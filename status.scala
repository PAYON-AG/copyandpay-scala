import java.io.DataOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import org.apache.commons.io.IOUtils

object status {
	def main(args: Array[String]) {
		println(getPaymentStatus("BD8A77F10083057D0ED1B6253755FA92.sbg-vm-tx02"))
	}
	def getPaymentStatus(checkoutId: String) : String  = {
	  val url = "https://test.oppwa.com/v1/checkouts/" + checkoutId + "/payment"
	 
	  val conn = new URL(url).openConnection().asInstanceOf[HttpsURLConnection]
	  conn.setRequestMethod("GET")

	  conn.connect()
	  if (conn.getResponseCode() >= 400) {
		return IOUtils.toString(conn.getErrorStream())
	  }
	  else {
		return IOUtils.toString(conn.getInputStream())
	  }
	}
}