
import com.anandbibek.web.notifications.R

// Define a data class to represent custom data fields
data class CustomData(
    val name: String,
    val description: String,
    val icon: Int,
    val url: String
)

// Sample data list
val customDataList = listOf(
    CustomData(
        name = "TPSC (Tripura)",
        description = "Tripura Public Services Commission",
        icon = R.drawable.service_24_hour,
        url = "https://tpsc.tripura.gov.in/"
    ),
    CustomData(
        name = "Item 2",
        description = "Description for Item 2",
        icon = R.drawable.service_24_hour,
        url = "https://example.com/item2"
    ),
    CustomData(
        name = "Item 3",
        description = "Description for Item 3",
        icon = R.drawable.service_24_hour,
        url = "https://example.com/item3"
    ),
    // Add more items here as needed
)