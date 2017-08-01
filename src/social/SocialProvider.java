package social;

public interface SocialProvider {

    void authorize();

    void onSuccess(String token);

    class Factory {

        public static SocialProvider create(SocialType type) {
            return new TwitterSocialProvider();
        }

    }

}
