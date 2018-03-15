package appsinthesky.com.firebaseread

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import appsinthesky.com.firebaseread.adapters.PersonCardAdapter
import appsinthesky.com.firebaseread.model.Person
import appsinthesky.com.firebaseread.model.PersonModel
import java.util.*

class MainActivity : AppCompatActivity(), Observer {

    private var mPersonListAdapter: PersonCardAdapter? = null
    private var mPersonObserver: Observer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        PersonModel

        val dataList: ListView = findViewById(R.id.person_list)


        val data:ArrayList<Person> = ArrayList()
        mPersonListAdapter = PersonCardAdapter(this, R.layout.person_card_item, data)
        dataList.adapter = mPersonListAdapter


    }

    override fun update(p0: Observable?, p1: Any?) {
        refreshList()
    }
    override fun onResume() {
        super.onResume()
        mPersonObserver = Observer { _, _ -> refreshList() }
        PersonModel.addObserver(mPersonObserver)
        refreshList()
    }

    private fun refreshList() {
        mPersonListAdapter?.clear()

        val data = PersonModel.getData()
        if (data != null) {
            mPersonListAdapter?.clear()
            mPersonListAdapter?.addAll(data)
            mPersonListAdapter?.notifyDataSetChanged()
        }
    }

    override fun onStop() {
        super.onStop()
        if (mPersonObserver != null) {
            PersonModel.deleteObserver(mPersonObserver)
        }
    }

    override fun onPause() {
        super.onPause()
        PersonModel.deleteObserver(this)
    }


}
