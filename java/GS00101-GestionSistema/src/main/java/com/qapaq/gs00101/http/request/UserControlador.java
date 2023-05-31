package com.qapaq.gs00101.http.request;

@RestController
@RequestMapping(value = "/users")
public class UserControlador {
    @GetMapping(path = "/")
    public String getUserInfo(Model model) {
    
        KeycloakAuthenticationToken authentication = 
          (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
    
        Principal principal = (Principal) authentication.getPrincipal();
    
        String userIdByToken = "";
    
        if (principal instanceof KeycloakPrincipal) {
            KeycloakPrincipal<KeycloakSecurityContext> kPrincipal = (KeycloakPrincipal<KeycloakSecurityContext>) principal;
            IDToken token = kPrincipal.getKeycloakSecurityContext().getIdToken();
            userIdByToken = token.getSubject();
        }
    
        model.addAttribute("userIDByToken", userIdByToken);
        return "userInfo";
    }    
}
