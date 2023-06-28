package com.example.myapplication

class AssemblyEmulator {

    private var register = mutableMapOf<String, Int>()

    private fun mov(registerName: String, value: Int) {
        register[registerName] = value
    }

    private fun add(destination: String, source1: String, source2: String) {
        val value1 = register[source1] ?: 0
        val value2 = register[source2] ?: 0
        register[destination] = value1 + value2
    }

    private fun sub(destination: String, source1: String, source2: String) {
        val value1 = register[source1] ?: 0
        val value2 = register[source2] ?: 0
        register[destination] = value1 - value2
    }

    private fun mul(destination: String, source1: String, source2: String) {
        val value1 = register[source1] ?: 0
        val value2 = register[source2] ?: 0
        register[destination] = value1 * value2
    }

    private fun div(destination: String, source1: String, source2: String) {
        val value1 = register[source1] ?: 0
        val value2 = register[source2] ?: 0
        if (value2 != 0) {
            register[destination] = value1 / value2
        } else {
            println("Error: Division by zero is not allowed")
        }
    }

    fun printRegister(registerName: String) {
        println("Register $registerName value: ${register[registerName]}")
    }

    fun executeInstruction(instruction: String) {
        val parts = instruction.split(" ")
        when (parts[0].toUpperCase()) {
            "MOV" -> mov(parts[1], parts[2].toInt())
            "ADD" -> add(parts[1], parts[2], parts[3])
            "SUB" -> sub(parts[1], parts[2], parts[3])
            "MUL" -> mul(parts[1], parts[2], parts[3])
            "DIV" -> div(parts[1], parts[2], parts[3])
            else -> println("Invalid instruction: $instruction")
        }
    }
}

