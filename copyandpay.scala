import java.io.DataOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import org.apache.commons.io.IOUtils

object copyandpay {
	def main(args: Array[String]) {
		println(prepareCheckout);
	}
	def prepareCheckout : String = {
	  val url = "https://test.oppwa.com/v1/checkouts"
	 
	  val parameters = ("authentication.userId=8a8294184b4f2868014b4f86f767015d"
		+ "&authentication.password=F8T7N4PD"
		+ "&authentication.entityId=8a8294184b4f2868014b4f87bf160173"
		+ "&paymentType=DB"
		+ "&amount=50.99"
		+ "&currency=EUR"
	  ) 
	  val conn = new URL(url).openConnection()

	  conn match {
		case secureConn: HttpsURLConnection  => secureConn.setRequestMethod("POST")
		case _ => throw new ClassCastException
	  }
	  conn.setDoInput(true)
	  conn.setDoOutput(true)
	  IOUtils.write(parameters, conn.getOutputStream())
	  conn.connect()
	 
	  return IOUtils.toString(conn.getInputStream())
	}
}