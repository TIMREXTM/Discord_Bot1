package main;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class NachrichtenReacktion extends ListenerAdapter {

    public void onMessageReceived (MessageReceivedEvent ereignis) {

        if (ereignis.isFromGuild()) {

            if (ereignis.getMessage().getContentStripped().equals("hallo")) {

                String username = ereignis.getAuthor().getName();

                ereignis.getChannel().sendTyping().queue();
                ereignis.getChannel().sendMessage("Hallo " + username + "!").queue();

                Role rolle = ereignis.getGuild().getRoleById("1136610329372135435");

                ereignis.getGuild().addRoleToMember(ereignis.getMember(), rolle).queue();

            } else if (ereignis.getMessage().getContentStripped().equals("!help")) {

                EmbedBuilder bauplan = new EmbedBuilder();


                bauplan.setTitle("Alle Commands");
                bauplan.setDescription("!help !view !setup role !setup ticket");
                bauplan.addField("'!view ' und dann den user auswählen", "test", true);
                bauplan.setColor(0x3262a8);

                bauplan.setImage("https://cdn.discordapp.com/attachments/1136652506219417640/1159871019394289754/command.png?ex=65329910&is=65202410&hm=0a23e4aa259d6229b809bfc8224aaeb7d219ca04f252e191f2c3701c0dcff23f&");


                ereignis.getChannel().sendMessageEmbeds(bauplan.build()).queue();

            }

        }

    }

}
