package appsinthesky.com.firebaseread.model

import com.google.firebase.database.DataSnapshot
import java.util.*


class Person(snapshot: DataSnapshot) {
    lateinit var id: String
    lateinit var firstName: String
    lateinit var lastName: String

    var skill: String? = ""


    init {
        try {
            val data: HashMap<String, Any> = snapshot.value as HashMap<String, Any>
            id = snapshot.key ?: ""
            firstName= data["firstName"] as String
            lastName = data["lastName"] as String
            skill = data["skill"] as String
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}