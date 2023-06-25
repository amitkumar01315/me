import com.fasterxml.jackson.core.JsonProcessingException;
import com.me.bo.dto.TransactionDto;
import com.me.bo.dto.UserDetailsDto;
import com.me.portal.MePortalUtility;

public class Test {

	public static void main(String[] args) throws JsonProcessingException {

		String api="http://192.168.1.41:8080/rest/me/v1/userDetails";
		String requestBody="{\"userName\":\"amit\",\"userPassword\":null,\"time\":null,\"ipAddress\":null}";
	

		System.out.println(MePortalUtility.getResposeFromBo( api, requestBody));

	}

}
