import java.io.DataOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import org.apache.commons.io.IOUtils

object copyandpayPAYOLUTION_INS {
	def main(args: Array[String]) {
		println(prepareCheckout);
	}
	def prepareCheckout : String = {
	  val url = "https://test.oppwa.com/v1/checkouts"
	 
	  val parameters = ("authentication.userId=8a8294174b7ecb28014b9699220015cc"
				+ "&authentication.password=sy6KJsT8"
				+ "&authentication.entityId=8a8294174b7ecb28014b9699a3cf15d1"
				+ "&paymentType=PA"
				+ "&amount=10.00"
				+ "&currency=EUR"
				+ "&customer.surname=Jones"
				+ "&customer.givenName=Jane"
				+ "&customer.birthDate=1970-01-01"
				+ "&billing.city=Test"
				+ "&billing.country=DE"
				+ "&billing.street1=123 Test Street"
				+ "&billing.postcode=TE1 2ST"
				+ "&customer.email=test@test.com"
				+ "&customer.phone=1234567890"
				+ "&customer.ip=123.123.123.123"
				+ "&customParameters[PAYOLUTION_ITEM_PRICE_1]=2.00"
				+ "&customParameters[PAYOLUTION_ITEM_DESCR_1]=Test item #1"
				+ "&customParameters[PAYOLUTION_ITEM_PRICE_1]=3.00"
				+ "&customParameters[PAYOLUTION_ITEM_DESCR_1]=Test item #2"
				+ "&testMode=EXTERNAL"
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