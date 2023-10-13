package main;

import command.ViewCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import javax.security.auth.login.LoginException;
import java.util.EnumSet;

public class hauptklasse {

    public static void main(String[] args) throws LoginException, InterruptedException {

        String prefix = "!";

        String status;
        status = "Den Server zu";

        String status2;
        status2 = "Ticket System";

        String status3;
        status3 = "Verify System";

        String token1 = "MTE0MzI1MDg5OTk4OTQzMDMxMg.GcposE.XUAtrFbLW85oiyWygv3oYXy5nibNfAaW2lTqDY";
        String token2 = "MTE2MDUwOTM2NzIzMDE1Njg0MA.GzZKpq.1qJGbG5JUIODX0CCiyfi75Vx5yp-K3GKPGVgOM";
        String token3 = "MTE2MDY2OTU5NjgxMTAxNDIzNA.G3mh5f.ONscOVMAeVFEhhTDW5oD1hBQgj2qEmUs837hXs";

        JDABuilder builder1 = JDABuilder.createDefault(token1);
        JDABuilder builder2 = JDABuilder.createDefault(token2);
        JDABuilder builder3 = JDABuilder.createDefault(token3);

        builder1.setStatus(OnlineStatus.ONLINE);
        builder1.setActivity(Activity.watching(status));
        builder1.setChunkingFilter(ChunkingFilter.ALL);
        builder1.setMemberCachePolicy(MemberCachePolicy.ALL);
        builder1.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS);

        EnumSet<CacheFlag> enumSet = EnumSet.of(CacheFlag.ONLINE_STATUS,CacheFlag.CLIENT_STATUS,CacheFlag.EMOJI,CacheFlag.VOICE_STATE);
        builder1.enableCache(enumSet);

        builder1.addEventListeners(new NachrichtenReacktion());
        builder1.addEventListeners(new WillkommensNachricht());
        builder1.addEventListeners(new ViewCommand());
        builder1.addEventListeners(new Rollenverteilung());


        builder2.setStatus(OnlineStatus.ONLINE);
        builder2.setActivity(Activity.watching(status2));
        builder2.setChunkingFilter(ChunkingFilter.ALL);
        builder2.setMemberCachePolicy(MemberCachePolicy.ALL);
        builder2.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS);

        EnumSet<CacheFlag> enumSet2 = EnumSet.of(CacheFlag.ONLINE_STATUS,CacheFlag.CLIENT_STATUS,CacheFlag.EMOJI,CacheFlag.VOICE_STATE);
        builder2.enableCache(enumSet2);

        builder2.addEventListeners(new TicketSystem());


        builder3.setStatus(OnlineStatus.ONLINE);
        builder3.setActivity(Activity.watching(status3));
        builder3.setChunkingFilter(ChunkingFilter.ALL);
        builder3.setMemberCachePolicy(MemberCachePolicy.ALL);
        builder3.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS);

        EnumSet<CacheFlag> enumSet3 = EnumSet.of(CacheFlag.ONLINE_STATUS,CacheFlag.CLIENT_STATUS,CacheFlag.EMOJI,CacheFlag.VOICE_STATE);
        builder3.enableCache(enumSet3);

        builder3.addEventListeners(new VerifySystem());


        JDA bot1 = builder1.build();
        JDA bot2 = builder2.build();
        JDA bot3 = builder3.build();
        System.out.println("Der Bot ist nun Online");
        System.out.println("Der Prefix des Bots lautet : " + prefix);


    }

}

