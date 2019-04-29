package dk.w4.websocketdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main2.*
import ua.naiksoftware.stomp.LifecycleEvent
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.client.StompClient


class Main2Activity : AppCompatActivity() {

    var client = Stomp.over(Stomp.ConnectionProvider.OKHTTP, URL)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        client.connect()

        client.topic("/topic/public").subscribe {
            Log.d(TAG, it.payload)
        }

        client.lifecycle().subscribe {<
            when(it.type){
                LifecycleEvent.Type.OPENED -> Log.d(TAG, "Stomp connection opened")
                LifecycleEvent.Type.CLOSED -> Log.d(TAG, "Stomp connection closed")
                LifecycleEvent.Type.ERROR -> Log.d(TAG, "Error", it.exception)
            }

        }

        btnSend.setOnClickListener {
            client.send("/app/chat/sendMessage", "Hello, World!").subscribe (
                { Log.d(TAG, "Message has been send") },
                { error -> Log.d(TAG, "Encountered error while sending data!", error) }
            )

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        client.disconnect()
    }


    companion object {
        const val TAG = "MainActivity"
        const val URL = "ws://192.168.87.16:8080/demo"
    }

}
