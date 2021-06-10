package mx.tecnm.tepic.ladm_u4_practica2_canvassensoresw

import android.graphics.*
import android.view.View

class Lienzo(p:MainActivity) : View(p){
    val imagenfoco1 = BitmapFactory.decodeResource(this.resources,R.drawable.focooff)
    val imagenfoco2 = BitmapFactory.decodeResource(this.resources,R.drawable.focoon)
    val carro = BitmapFactory.decodeResource(this.resources,R.drawable.carrito)
    var distancia = 0f
    var aceleracion = 5f
    var sentido = 1
    var posXCarrito = 0f
    override fun onDraw(c: Canvas) {
        super.onDraw(c)
        val pincel = Paint()
        pincel.textSize = 35f
        pincel.color = Color.BLACK
        c.drawText("Distancia: ${distancia}",10f,50f,pincel)
        if(distancia < 3f){
            c.drawColor(Color.WHITE)
            c.drawBitmap(imagenfoco1,250f,800f,pincel)
        }else{
            c.drawColor(Color.parseColor("#FFF7B3"))
            c.drawBitmap(imagenfoco2,250f,800f,pincel)
        }
        when(sentido){
            0->{
                if(posXCarrito>60f){
                    posXCarrito -= aceleracion
                }
            }
            1->{
                if(posXCarrito+carro.width<1000f){
                    posXCarrito += aceleracion
                }
            }
        }
        c.drawBitmap(carro,posXCarrito,100f,pincel)
        invalidate()
    }
}