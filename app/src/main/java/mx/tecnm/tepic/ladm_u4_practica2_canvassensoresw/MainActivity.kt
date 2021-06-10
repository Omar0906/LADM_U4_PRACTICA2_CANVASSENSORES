package mx.tecnm.tepic.ladm_u4_practica2_canvassensoresw

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity(), SensorEventListener {
    lateinit var sensorManager: SensorManager
    var sensorProx: Sensor? = null
    var sensorAcel: Sensor? = null
    lateinit var escuchaProx: SensorEventListener
    lateinit var canvas: Lienzo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        canvas = Lienzo(this)
        setContentView(canvas)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorProx = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        sensorAcel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            var distancia = event.values[0]
            canvas.distancia = distancia
        }
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            var x = event.values[0]
            if (x < -1) {
                canvas.sentido = 1
            }
            if (x > 1) {
                canvas.sentido = 0
            }

        }
    }

    override fun onResume() {
        super.onResume()

        sensorProx?.also { proximity ->
            sensorManager.registerListener(this, proximity, SensorManager.SENSOR_DELAY_NORMAL)
        }
        sensorAcel?.also { aceleration ->
            sensorManager.registerListener(this, aceleration, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}