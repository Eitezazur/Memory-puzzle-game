import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.memorypuzzlegame.R
import kotlinx.android.synthetic.main.item_memory_card.view.*

class MemoryGameAdapter(private val cardList: List<MemoryCard>, private val listener: OnCardClickListener) : RecyclerView.Adapter<MemoryGameAdapter.MemoryCardViewHolder>() {

    interface OnCardClickListener {
        fun onCardClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoryCardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_memory_card, parent, false)
        return MemoryCardViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemoryCardViewHolder, position: Int) {
        val card = cardList[position]
        holder.itemView.apply {
            // Set up card views here. Example:
            card_image.setImageResource(card.imageResId)  // Assuming MemoryCard has an imageResId property
            setOnClickListener { listener.onCardClick(position) }
        }
    }

    override fun getItemCount(): Int = cardList.size

    class MemoryCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}