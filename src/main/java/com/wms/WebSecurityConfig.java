package com.wms;

import com.wms.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()


                .antMatchers("/utilisateur-config","/modifierutilisateur","/ajouterutilisateur","/supprimerutilisateur",
                        "/transport","/saveTrnasport","/transportremove",
                        "/reception","/supprimerReception","/stockage","/ajouterFichierStock","/supprimerfichierStock","/detailreception","/detailfichierstock",
                        "/Clients","/fournisseurs","/ajouterclient","/ajouterfournisseur","/modifierperson","/supprimerperson",
                        "/preparationCommande","/livraison","/ajouterpreparationcommmande","/supprimerPCommande","/ajouterLivraison","/supprimerlivraison","/detaillivraison",
                        "/inventaire","/ajouterinventaire","/affichemodifierform","/supprimerinventaire","/journal",
                        "/emplacement","/emplacement/configuration","/supprimerEmplacement","/ajouterrangee","/supprimerToutRangee","/libérerToutRangee",
                        "/admin/dashboard",
                        "/article","/modifierarticle","/ajouterarticle","/supprimerarticle",
                        "/commande","/ajouterCommande","/supprimerCommande",
                        "/categories","/ajoutercategorie","/modifiercategorie","/supprimercategorie").hasAuthority("Admin")
                .antMatchers( "/emplacement","/emplacement/configuration","/supprimerEmplacement","/ajouterrangee","/supprimerToutRangee","/libérerToutRangee",
                        "/inventaire","/ajouterinventaire","/affichemodifierform","/supprimerinventaire","/journal").hasAnyAuthority("Admin","Opérateur")
                .antMatchers("/preparationCommande","/livraison","/ajouterpreparationcommmande","/supprimerPCommande","/ajouterLivraison","/supprimerlivraison","/detaillivraison",
                        "/commande","/ajouterCommande","/supprimerCommande",
                        "/reception","/supprimerReception","/stockage","/ajouterFichierStock","/supprimerfichierStock","/detailreception","/detailfichierstock").hasAnyAuthority("Admin","Agent Expédition/Réception")

                .antMatchers("/admin/dashboard","/").hasAnyAuthority("Admin","Agent Expédition/Réception","Opérateur")
                .anyRequest().permitAll()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().logoutSuccessUrl("/login").permitAll()
                .and().exceptionHandling().accessDeniedPage("/404")
                ;

        http.csrf().disable();
    }


}
