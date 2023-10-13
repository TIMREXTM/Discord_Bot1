package main;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;

public class Rollenverteilung extends ListenerAdapter {

    public void onMessageReceived (MessageReceivedEvent ereignis) {

        if (ereignis.getMessage().getContentStripped().equals("!setup role")) {

            EmbedBuilder builder = new EmbedBuilder();

            builder.setColor(0x3262a8);
            builder.setTitle("**:performing_arts: | Wähle deine Gaming Rollen aus!**");
            builder.setDescription("> Klicke auf das Menü unter dieser Nachricht, um deine Rollen auszuwählen!");
            builder.setImage("https://cdn.discordapp.com/attachments/1136652506219417640/1160253647456850021/GAMING_ROLLEN.gif?ex=6533fd6a&is=6521886a&hm=d3079918021a8c6cb5fdec87791ab8c85b3fe5b0690416dca64ae2a669c965c5&");

            SelectMenu menü = SelectMenu.create("Gaming")
                    .setPlaceholder("Wähle deine Gaming Rollen")
                    .setRequiredRange(0,1)
                    .addOption("Grand Theft Auto", "GTA", "Klicke, um das auszuwählen!")
                    .addOption("Fortnite", "Fortnite","Klicke, um das auszuwählen!")
                    .addOption("Call Of Duty", "CODY", "Klicke, um das auszuwählen!")
                    .build();

            ereignis.getChannel().sendMessageEmbeds(builder.build()).setActionRow(menü).queue();

        }

    }

    public void onSelectMenuInteraction (SelectMenuInteractionEvent ereignis) {

        if (ereignis.getSelectMenu().getId().equals("Gaming")) {

            Role rolle = null;

            switch (ereignis.getValues().get(0)) {

                case "GTA":

                    rolle = ereignis.getGuild().getRoleById("1159980741665505311");

                    break;

                case "Fortnite":

                    rolle = ereignis.getGuild().getRoleById("1159982579840188497");

                    break;

                case "CODY":

                    rolle = ereignis.getGuild().getRoleById("1159982477503377428");

                    break;

            }

            ereignis.getGuild().addRoleToMember(ereignis.getUser(),rolle).queue();
            ereignis.reply("Dir wurde die Rolle Erfolgreich gegeben!").setEphemeral(true).queue();

        }

    }

}
