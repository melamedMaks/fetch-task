package maksim.melamed.fetchtask.models

/*
model class that used for deserialization of json object
 */
data class Data(
    val id: Int,
    val listId: Int,
    val name: String?
)