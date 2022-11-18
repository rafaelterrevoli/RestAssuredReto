package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class FeaturesSteps {

    @Given("^I send a request GET to the (.+) to get id (.+) and name (.+)$")
    public void iSendARequestGETToTheUserToGetUseridAndName(String endpoint, String userid, String name) {
        Response response = when().get(endpoint);
        assertThat(response.getStatusCode(),equalTo(200));
        assertThat(response.jsonPath().getString("id["+userid+"]"),equalTo("10"));
        assertThat(response.jsonPath().getString("name["+userid+"]"),equalTo(name));
    }

    @And("^I send a request GET to (.+) and validate that userid (.+) the titles and bodies are not empty$")
    public JSONArray iSendARequestGETToPostsAndValidateThatUseridTheTitlesAndBodiesAreNotEmpty(String endpoint, int id) {
        Response response = when().get(endpoint);
        assertThat(response.getStatusCode(), equalTo(200));
        String responseBody = response.getBody().asString();
        JSONArray arregloJson = new JSONArray(responseBody);
        for (int i = 0; i < arregloJson.length(); i++) {
            JSONObject posts = arregloJson.getJSONObject(i);
            int userId = posts.getInt("userId");
            String title = posts.getString("title");
            String body = posts.getString("body");
            if (userId == id) {
                assertThat(title.isEmpty(), is(false));
                assertThat(body.isEmpty(), is(false));
            }
        }
        return arregloJson;
    }

    @Given("^I send a request GET to (.+) get id (.+) validate that name and username are not empty$")
    public void iSendARequestGETToUsersGetIdValidateThatNameAndUsernameAreNotEmpty(String endpoint, String id) {
        Response response = when().get(endpoint);
        assertThat(response.getStatusCode(),equalTo(200));
        int idIni = Integer.parseInt(id) - 1;
        String idEnd = String.valueOf(idIni);
        assertThat(response.jsonPath().getString("id["+idEnd+"]"),equalTo(id));
        String name = response.jsonPath().getString("name["+idEnd+"]");
        assertThat(name.isEmpty(), is(false));
        String userName = response.jsonPath().getString("username["+idEnd+"]");
        assertThat(userName.isEmpty(), is(false));
        if (idIni == 0) {
            System.out.println("Nombre: " + name);
        }
        if (idIni == 4){
            String responseBody = response.getBody().asString();
            System.out.println("Todos los datos de la respuesta:");
            System.out.println(responseBody);
        }
    }

    int postID = 0;
    @Given("^I send a request GET to (.+) and validate that userid (.+) the titles and bodies are not empty print postId$")
    public void iSendARequestGETToPostsAndValidateThatUseridTheTitlesAndBodiesAreNotEmptyPrintPostId(String endpoint, int id) {
        JSONArray arregloJson = iSendARequestGETToPostsAndValidateThatUseridTheTitlesAndBodiesAreNotEmpty(endpoint, id);
        boolean sw = false;
        for (int i = 0; i < arregloJson.length(); i++) {
            JSONObject posts = arregloJson.getJSONObject(i);
            int userId = posts.getInt("userId");
            if (userId != id && sw == false ) {
                posts = arregloJson.getJSONObject(i - 1);
                postID = posts.getInt("id");
                System.out.println("El ultimo postId del userId numero "+ id +" es: "+ postID);
                sw = true;
            }
        }
    }

    @And("^I look for all the comments of the postid print the name and email of the first comment$")
    public void iLookForAllTheCommentsOfThePostidPrintTheNameAndEmailOfTheFirstComment() {
        Response response = when().get("comments");
        assertThat(response.getStatusCode(),equalTo(200));
        String responseBody = response.getBody().asString();
        JSONArray arregloJson = new JSONArray(responseBody);
        boolean sw = false;
        for (int i = 0; i < arregloJson.length(); i++) {
            JSONObject posts = arregloJson.getJSONObject(i);
            int postIdAx = posts.getInt("postId");
            if (postIdAx == postID && sw == false ) {
                String name = posts.getString("name");
                String email = posts.getString("email");
                System.out.println("name del primer comentario: " + name);
                System.out.println("email del primer cometario: " + email);
                sw = true;
            }
        }
    }












        /*
        List<String> myList = new ArrayList<String>(Arrays.asList(responseBody.split(",")));

        for (String i : myList) {
            System.out.println("Correo: " + i);
        }
            */
        /*
        Map<String, String> test = response.jsonPath().getMap("data");
        System.out.println(test);
        String id = test.get("id");
        System.out.println("ID : " + id);

        */
        /*
        List<Integer> ListuserId = response.jsonPath().getList("userId");
        List<Integer> ListuserId = response.jsonPath().getMap();
            //System.out.println("id: " + i);
            if(i == 10) {
                System.out.println(response.jsonPath().getString("title"));
            }
        }
    */
        /*
        List<String> ListResponse = response.jsonPath().getList("title");
        int longList = ListResponse.size();
        System.out.println(longList);

        for (String i : ListResponse) {
            System.out.println("Correo: " + i);
        }*/

}
