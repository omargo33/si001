import { UserManager } from 'oidc-client';

/* scope: "openid profile message.read",*/

const settings = {
  authority: "http://localhost:8080/realms/my-realm",
  client_id: "my-client",
  redirect_uri: "http://localhost:3000/signin-callback.html",
  response_type: 'code',
  scope: "email",
};

const userManager = new UserManager(settings);

export const getUser = () => {
    return userManager.getUser();
}

export const login = () => {
    return userManager.signinRedirect();
}

export const logout = () => {
    return userManager.signoutRedirect();
}
