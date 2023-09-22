package com.example.actividad18_09_2023

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class detalle : AppCompatActivity() {

    //Intancias
    private lateinit var datos: TextView
    private lateinit var objProducto: Producto

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        //Asociar instancis con componentes graficos
        datos = findViewById(R.id.txtDatos)
        //Inicializar instancia
        objProducto = Producto()
        //instancia para recibir informacion
        val infoRecibida = intent.extras

        //Extraer informacion y colocar en el objeto

        //Primera forma
        val also = infoRecibida?.getInt("codigo")!!.also { objProducto.codigo = it }
        infoRecibida?.getString("descripcion")!!.also { objProducto.descripcion = it }
        infoRecibida?.getString("marca")!!.also { objProducto.marca = it }
        objProducto.talla = infoRecibida?.getInt("talla")!!
        objProducto.costo = infoRecibida?.getDouble("costo")!!
        objProducto.cantidad = infoRecibida?.getInt("cantidad")!!
        //Definir la talla
        var talla : String? = null
        if(objProducto.talla == 1) talla = "Talla Chica"
        if(objProducto.talla == 2) talla = "Talla Mediana"
        if(objProducto.talla == 3) talla = "Talla Grande"
        //colocar la informacion en el textView
        datos.text = "\nCodigo: "+objProducto.codigo +
                "\n Descripcion: "+objProducto.descripcion +
                "\n Marca: "+objProducto.marca +
                "\n Talla: "+objProducto.talla +
                "\n Costo: "+objProducto.costo +
                "\n Cantidad: "+objProducto.cantidad
    }//onCreate

    fun regresar(view: View){
        //Intancia para lanzar activivity Detalle
        val intent = Intent(this, spinner::class.java)
        //val intent = Intent(this, MainActivity::class.java)
        //Lanzar la activity
        startActivity(intent)
    }
}//class