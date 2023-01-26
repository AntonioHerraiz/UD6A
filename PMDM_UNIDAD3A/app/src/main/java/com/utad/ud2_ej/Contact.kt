package com.utad.ud2_ej

import java.lang.Boolean.FALSE
import java.lang.Boolean.TRUE


class Contact(val img: Int, val name: String, val email: String, val isOnline: Boolean) {

    companion object {
        private var lastContactId = 0
        fun createContactsList(numContacts: Int): ArrayList<Contact> {
            val contacts = ArrayList<Contact>()
            /*for (i in 1..numContacts) {
                contacts.add(Contact("Person " + ++lastContactId, i <= numContacts / 2))
            }*/

            contacts.add(Contact(R.drawable.avatar_13140ac259a867eb11595ec0308fa0c1,"Antonio Perez", "ant.perez@gmail.com", TRUE))
            contacts.add(Contact(R.drawable.avatar_05d43d26540bf71f2d5ec4ebfa1e45f6,"Elena Lopez", "e.lopez@gmail.com", TRUE))
            contacts.add(Contact(R.drawable.avatar_1aa840d8beb1a997399e5f752fecbe74,"Pablo Nin", "p.nin@gmail.com", TRUE))
            contacts.add(Contact(R.drawable.avatar_49602e486edf29bafaa1c4733cf0a987,"Ana Martinez", "a.martinez@gmail.com", FALSE))
            contacts.add(Contact(R.drawable.avatar_d2c543089a2ebaea92f2b142befb1310,"Lidia Luna", "l.luna@gmail.com", TRUE))
            contacts.add(Contact(R.drawable.avatar_4a3d8458961911104a2ef0a0c8d3771f,"Adolfo Gonzalez", "a.gonzalez@gmail.com", TRUE))
            contacts.add(Contact(R.drawable.avatar_61399ebfb1bf27ea30f5fac9c2f73eb4,"Marta Lopez", "m.lopez@gmail.com", FALSE))
            contacts.add(Contact(R.drawable.avatar_76fa112f5d0024ec60d4c3acd33c2f8f,"Jenny Maria", "j.maria@gmail.com", TRUE))
            contacts.add(Contact(R.drawable.avatar_86004c75e9402925131c86fe48010ea1,"Jorge Perez", "j.perez@gmail.com", TRUE))
            contacts.add(Contact(R.drawable.avatar_a2bb8c0aca8f8d1af86893903499b861,"Manuel Muro", "m.muro@gmail.com", TRUE))

            //contacts.add(Contact("Antonio Perez", "ant.perez@gmail.com", TRUE))
            //contacts.add(Contact("Antonio Perez", "ant.perez@gmail.com", TRUE))

            return contacts
        }
    }
}