package appsinthesky.com.firebaseread.model

import android.util.Log
import com.google.firebase.database.*
import java.util.*

object PersonModel: Observable() {

    private var m_valueDataListener: ValueEventListener? = null     // The data listener that gets the data from the database
    private var m_PersonList: ArrayList<Person>? = ArrayList()    // Person cache

    //gets the database location reference for later repeated use
    private fun getDatabaseRef(): DatabaseReference? {
        return FirebaseDatabase.getInstance().reference.child("Person")
    }

    //called on object initialisation
    init {
        if (m_valueDataListener != null) {

            getDatabaseRef()?.removeEventListener(m_valueDataListener)
        }
        m_valueDataListener = null
        Log.i("PersonModel", "dataInit line 27")


        m_valueDataListener = object: ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                try {
                    Log.i("PersonModel", "data updated line 28")
                    val data: ArrayList<Person> = ArrayList()
                    if (dataSnapshot != null) {
                        for (snapshot: DataSnapshot in dataSnapshot.children) {
                            try {
                                data.add(Person(snapshot))
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                        m_PersonList = data
                        Log.i("PersonModel","data updated there are " + m_PersonList!!.size + " Person in the list")
                        setChanged()
                        notifyObservers()
                    } else {
                        throw Exception("data snapshot is null line 31")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onCancelled(p0: DatabaseError?) {
                if (p0 != null) {
                    Log.i("PersonModel", "line 33 Data update cancelled, err = ${p0.message}, detail = ${p0.details}")
                }
            }
        }
        getDatabaseRef()?.addValueEventListener(m_valueDataListener)
    }

    fun getData(): ArrayList<Person>? {
        return m_PersonList
    }
}