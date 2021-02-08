package com.example.shop

import com.example.shop.MainActivity
import com.example.shop.R
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*

class register : AppCompatActivity() {
    lateinit var lang:String
    lateinit var dao:DAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        lang=intent.getStringExtra("lang").toString()
        set_language()
        cguview.setOnClickListener{
            var i = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.fr/gp/help/customer/display.html?nodeId=GLSBYFE9MGKKQXXM"))
            startActivity(i)
        }
        register_register.setOnClickListener{
            errorview_register.text=""
            var valid=true
            if (! android.util.Patterns.EMAIL_ADDRESS.matcher(emailedit_register.text.toString()).matches() ){
                errorview_register.text=errorview_register.text.toString()+"Veuillez saisir une adresse e-mail valide\n"
                valid=false
            }
            if (passwordedit_register.text.toString().length<6 ){
                errorview_register.text=errorview_register.text.toString()+"Votre mot de passe doit contenir au moins 6 caractères\n"
                valid=false
            }
            if (passwordedit_register.text.toString()!=passwordconfirmedit_register.text.toString() ){
                errorview_register.text=errorview_register.text.toString()+"Les deux mots de passes saisis \n"
                valid=false
            }
            if(!cgubox.isChecked){
                errorview_register.text=errorview_register.text.toString()+"Vous devez approuver les conditions d'utilisation générales\n"
                valid=false
            }
            if(valid) {

                Toast.makeText(this, "Vous vous êtes inscrits !", Toast.LENGTH_SHORT)
                var i = Intent(this, MainActivity::class.java)
                var u = User(emailedit_register.text.toString(), passwordedit_register.text.toString(), "" + birthdatedatepicker_register.year + "-" + birthdatedatepicker_register.month + "-" + birthdatedatepicker_register.dayOfMonth)
                dao.ajouter(u)
                startActivity(i)
            }
        }
        init()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.lang_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId){
            R.id.fr_lang -> lang="fr"
            R.id.en_lang -> lang="en"
        }
        set_language()
        return super.onOptionsItemSelected(item)
    }

    fun set_language(){
        if(lang=="fr"){
            register_register.text=getString(R.string.register_fr)
            birthdateview_register.text=getString(R.string.birthdate_fr)
            passwordview_register.text=getString(R.string.password_fr)
            passwordconfirmview_register.text=getString(R.string.passwordconfirm_fr)
            emailview_register.text=getString(R.string.email_fr)
            cguview.text=getString(R.string.cguview_fr)
            cgubox.text=getString(R.string.cgubox_fr)
        }
        if(lang=="en"){
            register_register.text=getString(R.string.register_en)
            birthdateview_register.text=getString(R.string.birthdate_en)
            passwordview_register.text=getString(R.string.password_en)
            passwordconfirmview_register.text=getString(R.string.passwordconfirm_en)
            emailview_register.text=getString(R.string.email_en)
            cguview.text=getString(R.string.cguview_en)
            cgubox.text=getString(R.string.cgubox_en)
        }

    }
    private fun init() {
        dao=DAO(this)
    }


}