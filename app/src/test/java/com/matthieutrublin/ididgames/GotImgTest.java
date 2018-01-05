package com.matthieutrublin.ididgames;

import org.json.JSONObject;
import org.junit.Test;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by matthieutrublin on 02/01/2018.
 */

public class GotImgTest {
    @Test
    public void makeHashTag_isEmpty() throws Exception {
        assertThat(MainActivity.makeHashTag(""), is("boat"));
    }

    @Test
    public void makeHashTag_isFullOfShit() throws Exception {
        assertThat(MainActivity.makeHashTag("fl ow<e>\nrs!§"), is("flowers"));
    }

    /*@Test
    public void gotImgTest_search() throws Exception {
        JSONObject jsonObject = new JSONObject("{\"comments_disabled\": false,\"id\": \"1683424557023088101\",\"dimensions\": {\"height\": 1350, \"width\": 1080},\"owner\": {\"id\": \"6769693566\"},\"thumbnail_src\": \"https://scontent-cdt1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/c0.135.1080.1080/26066259_347923255676483_8261584325040930816_n.jpg\", \"thumbnail_resources\": [{\"src\": \"https://scontent-cdt1-1.cdninstagram.com/t51.2885-15/s150x150/e35/c0.135.1080.1080/26066259_347923255676483_8261584325040930816_n.jpg\", \"config_width\": 150, \"config_height\": 150}, {\"src\": \"https://scontent-cdt1-1.cdninstagram.com/t51.2885-15/s240x240/e35/c0.135.1080.1080/26066259_347923255676483_8261584325040930816_n.jpg\", \"config_width\": 240, \"config_height\": 240}, {\"src\": \"https://scontent-cdt1-1.cdninstagram.com/t51.2885-15/s320x320/e35/c0.135.1080.1080/26066259_347923255676483_8261584325040930816_n.jpg\", \"config_width\": 320, \"config_height\": 320}, {\"src\": \"https://scontent-cdt1-1.cdninstagram.com/t51.2885-15/s480x480/e35/c0.135.1080.1080/26066259_347923255676483_8261584325040930816_n.jpg\", \"config_width\": 480, \"config_height\": 480}, {\"src\": \"https://scontent-cdt1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/c0.135.1080.1080/26066259_347923255676483_8261584325040930816_n.jpg\", \"config_width\": 640, \"config_height\": 640}], \"is_video\": false, \"code\": \"Bdcubcnh83l\", \"date\": 1514899867,\"display_src\": \"https://scontent-cdt1-1.cdninstagram.com/t51.2885-15/e35/26066259_347923255676483_8261584325040930816_n.jpg\",\"caption\": \"golden waves \ud83c\udf0a\ud83d\ude0d\n.\n\ud83d\udcf8 by @_enk \nbig pleasure to have u on #instagram -\n.\n\u217dongratulation \n\u1d3e\u1d35\u1d9c\u1d40\u1d41\u1d3f\u1d31 \u1d3c\u1da0 \u1d40\u1d34\u1d31 \u1d39\u1d3c\u1d39\u1d31\u1d3a\u1d40 \ud83c\udfc6 .\n.\nFollow us @book_of_shots \ud83d\udcd6 Tag #gotashotforthebook\n.\n.\n.\n.\n.\n#gold #2018 #venice #venezia #italy #italia #instagramhub #boat #waves #sunshine #ship #city #colors_of_day #sunset #artofvisual #happyholiday #photography #instagood #instadaily #vacation #photo #follow #cityoflove #shotzdelight #romance #inlove #instaphoto #instaitaly\",\"comments\": {\"count\": 0}, \"likes\": {\"count\": 0}}");
        ImageInstagram img=new ImageInstagram(jsonObject);
        assertThat(img.identifier, is("1683424557023088101"));
        assertThat(img.owner, is("6769693566"));
        assertThat(img.url, is("https://scontent-cdt1-1.cdninstagram.com/t51.2885-15/e35/26066259_347923255676483_8261584325040930816_n.jpg"));
        assertThat(img.smallSizeUrl, is("https://scontent-cdt1-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/c0.135.1080.1080/26066259_347923255676483_8261584325040930816_n.jpg"));
        assertThat(img.isVideo, is(false));
        assertThat(img.code, is("Bdcubcnh83l"));
        assertThat(img.date, is(ImageInstagram.getDate("1514899867")));
        assertThat(img.caption, is("golden waves \ud83c\udf0a\ud83d\ude0d\n.\n\ud83d\udcf8 by @_enk \nbig pleasure to have u on #instagram -\n.\n\u217dongratulation \n\u1d3e\u1d35\u1d9c\u1d40\u1d41\u1d3f\u1d31 \u1d3c\u1da0 \u1d40\u1d34\u1d31 \u1d39\u1d3c\u1d39\u1d31\u1d3a\u1d40 \ud83c\udfc6 .\n.\nFollow us @book_of_shots \ud83d\udcd6 Tag #gotashotforthebook\n.\n.\n.\n.\n.\n#gold #2018 #venice #venezia #italy #italia #instagramhub #boat #waves #sunshine #ship #city #colors_of_day #sunset #artofvisual #happyholiday #photography #instagood #instadaily #vacation #photo #follow #cityoflove #shotzdelight #romance #inlove #instaphoto #instaitaly"));
        assertThat(img.likes, is(0));
        assertThat(img.comments, is(0));
    }*/
}
