package discord;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

public class SetupDiscordBot {
	
	public static void main(String[] args) {
		try {
			JDA bot = new JDABuilder("NTgyODMyNDM5NzgxNjIxNzgz.XOzlmA.T0b1IRsJSgCBZZ26w3c9FGzLW78")
					.addEventListener(new Bot())
					.build()
					.awaitReady();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
