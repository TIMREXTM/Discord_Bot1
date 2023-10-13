package main;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class WillkommensNachricht extends ListenerAdapter {

    public void onGuildMemberJoin (GuildMemberJoinEvent ereignis) {

        User nutzer = ereignis.getUser();

        EmbedBuilder bauplan = new EmbedBuilder();

        bauplan.setTitle("Willkommen auf dem Server");
        bauplan.setDescription("Danke " + nutzer.getAsMention() + " dass du unserem Server Beigetreten bist!, verifiziere dich zu erst im <#1150444390691655752> channel!");
        bauplan.setColor(0x3262a8);

        bauplan.setImage("https://cdn.discordapp.com/attachments/1136652506219417640/1160208840592207902/Willkommen.gif?ex=6533d3af&is=65215eaf&hm=e07b047ae68bad9c5995870ebad2de98bef7d1d1d9a4d1cd759882914b33cd38&");

        Role rolle = ereignis.getGuild().getRoleById("1136610329372135435");
        ereignis.getGuild().addRoleToMember(ereignis.getMember(), rolle).queue();

        ereignis.getGuild().getTextChannelById("1136056562583089155").sendMessageEmbeds(bauplan.build()).queue();
        nutzer.openPrivateChannel().complete().sendMessageEmbeds(bauplan.build()).queue();

    }

}
