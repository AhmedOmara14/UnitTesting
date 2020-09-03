package maze.run.unittestingroomretrofitmmvm.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Shopping")
data class ShoppingItem(
    var name: String,
    var amount: Int,
    var price: Float,
    var imgageRrl: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)