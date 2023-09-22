import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.udea.compumovil.gr06_20232.lab1.APIService
import co.edu.udea.compumovil.gr06_20232.lab1.Country
import kotlinx.coroutines.launch

class CountryViewModel : ViewModel() {
    private val _countryList = mutableListOf<Country>()
    var errorMessage: String by mutableStateOf("")
    val countryList: List<Country>
        get() = _countryList

    fun getCountryList() {
        viewModelScope.launch {
            val apiService = APIService.getCountryAPIInstance()
            try {
                _countryList.clear()
                _countryList.addAll(apiService.getCountries())
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}
