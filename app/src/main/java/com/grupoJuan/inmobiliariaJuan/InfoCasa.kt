package com.grupoJuan.inmobiliariaJuan

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_info_casa.*
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.bumptech.glide.Glide


class InfoCasa : AppCompatActivity() {
    private lateinit var imageView: ImageView

    companion object {
        const val EXTRA_TITLE = "title"
        const val EXTRA_IMAGE = "image"
        const val EXTRA_DESCRIPTION = "description"
        const val EXTRA_PRICE = "price"
        const val EXTRA_URL = "url"


        fun newIntent(context: Context, casa: Casa): Intent {
            val detailIntent = Intent(context, InfoCasa::class.java)

            detailIntent.putExtra(EXTRA_TITLE, casa.title)
            detailIntent.putExtra(EXTRA_IMAGE, casa.imageUrl)
            detailIntent.putExtra(EXTRA_DESCRIPTION, casa.description)
            detailIntent.putExtra(EXTRA_PRICE, casa.price)
            detailIntent.putExtra(EXTRA_URL, casa.url)


            return detailIntent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_casa)

        val title = intent.extras?.getString(EXTRA_TITLE)
        val image = intent.extras?.getString(EXTRA_IMAGE)
        val description = intent.extras?.getString(EXTRA_DESCRIPTION)
        val price = intent.extras?.getString(EXTRA_PRICE)
        val url = intent.extras?.getString(EXTRA_URL)

        //titulo
        casas_list_title2.setText(title)

        //imagen
        imageView = findViewById(R.id.casas_list_thumbnail2)
        Glide.with(this).load(image).into(imageView)

        //descripcion
        textView2.setText(description)

        //precio
        casas_list_detail2.setText(price)


        ubicacion.setOnClickListener() {

            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(url)
            )
            startActivity(intent)

        }



	 contactar.setOnClickListener {
         val builder = AlertDialog.Builder(this@InfoCasa)
         builder.setTitle("FORMAS DE CONTACTAR")
         builder.setMessage("Elija la forma de contactar")
         builder.setPositiveButton("SMS") { dialog, which ->
             // Hace algo cuando le damos al boton
             val smsIntent = Intent(Intent.ACTION_SENDTO)
             smsIntent.addCategory(Intent.CATEGORY_DEFAULT)
             smsIntent.type = "vnd.android-dir/mms-sms"
             smsIntent.data = Uri.parse("sms:" + 601247324)

             startActivity(smsIntent)

         }
         builder.setNegativeButton("CORREO") { dialog, which ->
             val destinatario= arrayOf("migprado175@gmil.com")
           correoS("Información sobre la vivienda: $title",destinatario)

         }

         builder.show()

     }


    }
    fun correoS(subject:String,destinatario:Array<String>){
        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data=Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL,destinatario)
            putExtra(Intent.EXTRA_SUBJECT, subject)

        }
        startActivity(Intent.createChooser(emailIntent, "Enviar email..."))
    }



}


