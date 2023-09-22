package com.example.actividad18_09_2023

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    //Instancias
    private lateinit var codigo: EditText
    private lateinit var descripcion: EditText
    private lateinit var costo: EditText
    private lateinit var cantidad: EditText
    private lateinit var marcas: ListView
    private lateinit var tallas: RadioGroup
    private lateinit var tChica: RadioButton
    private lateinit var tMediana: RadioButton
    private lateinit var tGrande: RadioButton
    private lateinit var objProducto: Producto
    private var marcaSel: String = "Hermes"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Asociar instancias con componentes graficos

        codigo = findViewById(R.id.editCodigo)
        descripcion = findViewById(R.id.editDescripcion)
        costo = findViewById(R.id.editCosto)
        cantidad = findViewById(R.id.editCantidad)
        marcas = findViewById(R.id.ltvMarca)
        tallas = findViewById(R.id.rgpTallas)
        tChica = findViewById(R.id.rbtChica)
        tMediana = findViewById(R.id.rbtMediana)
        tGrande = findViewById(R.id.rbtGrande)



        //Inicializar instancia
        objProducto = Producto()

        //Arreglo de marcas
        val lstMarca = listOf("Hermes","Dior","Gucci","Chanel","Prada")
        //Definir el tipo de lista y relacion con las marcas
        val adaptador = ArrayAdapter(this,android.R.layout.simple_list_item_1,lstMarca)
        //Asociar adaptador con instancia
        marcas.adapter = adaptador
        //Escucha para la lista de marca y almacenar el valor seleccionado
        marcas.setOnItemClickListener{parent,view, position, id->
            marcaSel = parent.getItemAtPosition(position).toString()
            Toast.makeText(this,"Marca: $marcaSel",Toast.LENGTH_LONG).show()
        }

    }//onCreate

    fun onClick(v: View?){
        when(v?.id){
            R.id.ibtnAgregar -> agregar()
            R.id.ibtnMostrar -> mostrar()
            R.id.ibtnLimpiar -> limpiar()
        }//when
    }//onClick

    private fun agregar(){
        //Validar que cajas de texto Codigo y Descripcion tengan informacion
        if(codigo.text.isNotEmpty() && codigo.text.isNotEmpty() &&
            descripcion.text.isNotEmpty() && descripcion.text.isNotEmpty()){
            //Agregar los valores al objeto
            objProducto.codigo = codigo.text.toString().toInt()
            objProducto.descripcion = descripcion.text.toString()
            objProducto.marca = marcaSel
            if(tChica.isChecked) objProducto.talla = 1
            if(tMediana.isChecked) objProducto.talla = 2
            if(tGrande.isChecked) objProducto.talla = 3
            objProducto.costo = costo.text.toString().toDouble()
            objProducto.cantidad = cantidad.text.toString().toInt()
            //Mensaje informativo
            Toast.makeText(this,"Producto Registrado.",Toast.LENGTH_LONG).show()
            //Limpiar Componentes
            limpiar()
        }else{
            //En caso que esten vacias las cajas de Codigo y Descripcion, mostrar mensaje
            Toast.makeText(this,"Debe capturar datos.",Toast.LENGTH_LONG).show()
        }//if-else
    }//agregar

    private fun mostrar(){
        //Instancia para lanzar Activity Detalle
        val intent = Intent(this,detalle::class.java)
        //Agregar los parametros para enviar a la Activity
        intent.putExtra("codigo",objProducto.codigo)
        intent.putExtra("descripcion",objProducto.descripcion)
        intent.putExtra("marca",objProducto.marca)
        intent.putExtra("talla",objProducto.talla)
        intent.putExtra("costo",objProducto.costo)
        intent.putExtra("cantidad",objProducto.cantidad)
        //Lanzar la Activity
        startActivity(intent)
    }//Mostrar

    private fun limpiar(){
        codigo.text = null
        descripcion.text = null
        costo.text.clear()
        cantidad.text = null
        tallas.clearCheck()
        codigo.requestFocus()
    }//Limpiar
}//class