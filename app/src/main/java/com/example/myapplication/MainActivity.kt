package com.example.myapplication

import android.graphics.PointF
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.exceptions.RealmPrimaryKeyConstraintException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initRealm()
        assemblyDemo()

        setContentView(R.layout.activity_main)
        val db = Realm.getDefaultInstance()
        val points = db.where(Point::class.java).findAll()
        val nodePositions = mutableListOf<PointF>()
        points.forEach { p ->
            nodePositions.add(PointF(p.x.toFloat(), p.y.toFloat()))
        }

        val graphView = GraphView(this, nodePositions)

        val frameLayout = findViewById<FrameLayout>(R.id.frame_layout_1)
        frameLayout.addView(graphView)
    }

    private fun assemblyDemo() {

        val emulator = AssemblyEmulator()

        println("Enter two integers:")
        val num1 = 4
        val num2 = 5

        emulator.executeInstruction("MOV R1 $num1")
        emulator.executeInstruction("MOV R2 $num2")

        println("Enter an operator (+, -, *, /):")
        val operator = "*"

        when (operator) {
            "+" -> {
                emulator.executeInstruction("ADD R3 R1 R2")
                emulator.printRegister("R3")
            }

            "-" -> {
                emulator.executeInstruction("SUB R3 R1 R2")
                emulator.printRegister("R3")
            }

            "*" -> {
                emulator.executeInstruction("MUL R3 R1 R2")
                emulator.printRegister("R3")
            }

            "/" -> {
                if (num2 != 0) {
                    emulator.executeInstruction("DIV R3 R1 R2")
                    emulator.printRegister("R3")
                } else {
                    println("Error: Division by zero is not allowed")
                }
            }

            else -> println("Invalid operator")
        }

    }

    private fun initRealm() {
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .name("test.db")
            .schemaVersion(1)
            .build()
        Realm.setDefaultConfiguration(config)
        val db = Realm.getDefaultInstance()
        db.executeTransactionAsync {
            val p1 = Point().apply {
                id = "100_100"
                x = 100
                y = 100
            }
            try {
                it.insert(p1)
            } catch (_: RealmPrimaryKeyConstraintException) {
            }
        }
        db.executeTransactionAsync {
            val p2 = Point().apply {
                id = "200_200"
                x = 200
                y = 200
            }
            try {
                it.insert(p2)
            } catch (_: RealmPrimaryKeyConstraintException) {
            }
        }
        db.executeTransactionAsync {
            val p3 = Point().apply {
                id = "300_300"
                x = 300
                y = 300
            }
            try {
                it.insert(p3)
            } catch (_: RealmPrimaryKeyConstraintException) {
            }
        }
    }
}