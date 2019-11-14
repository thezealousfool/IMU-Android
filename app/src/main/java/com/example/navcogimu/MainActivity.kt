package com.example.navcogimu

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var _sensorManager : SensorManager
    private lateinit var _acc : Sensor
    private lateinit var _gyro : Sensor
    private lateinit var _grav : Sensor
    private lateinit var _mag : Sensor

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        when(event?.sensor?.type) {
            Sensor.TYPE_GYROSCOPE -> {
                gyroXValue.text = "%.2f".format(event?.values?.get(0)?:0)
                gyroYValue.text = "%.2f".format(event?.values?.get(1)?:0)
                gyroZValue.text = "%.2f".format(event?.values?.get(2)?:0)
            }
            Sensor.TYPE_GRAVITY -> {
                accXValue.text = "%.2f".format(event?.values?.get(0)?:0)
                accYValue.text = "%.2f".format(event?.values?.get(1)?:0)
                accZValue.text = "%.2f".format(event?.values?.get(2)?:0)
            }
            Sensor.TYPE_MAGNETIC_FIELD -> {
                magXValue.text = "%.2f".format(event?.values?.get(0)?:0)
                magYValue.text = "%.2f".format(event?.values?.get(1)?:0)
                magZValue.text = "%.2f".format(event?.values?.get(2)?:0)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        _sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        _acc = _sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)
        _gyro = _sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        _grav = _sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)
        _mag = _sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

    }

    override fun onResume() {
        super.onResume()
        _sensorManager.registerListener(this, _acc, SensorManager.SENSOR_DELAY_NORMAL)
        _sensorManager.registerListener(this, _gyro, SensorManager.SENSOR_DELAY_NORMAL)
        _sensorManager.registerListener(this, _grav, SensorManager.SENSOR_DELAY_NORMAL)
        _sensorManager.registerListener(this, _mag, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        _sensorManager.unregisterListener(this)
    }
}
