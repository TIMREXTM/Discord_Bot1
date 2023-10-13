package command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.util.concurrent.TimeUnit;

public class ViewCommand extends ListenerAdapter {

    public void onMessageReceived (MessageReceivedEvent ereignis) {

        if (ereignis.getMessage().getContentStripped().startsWith("!view")) {

            Member mitglied = ereignis.getMessage().getMentions().getMembers().get(0);

            EmbedBuilder bauplan = new EmbedBuilder();
            bauplan.setThumbnail(mitglied.getEffectiveAvatarUrl());
            bauplan.setTitle("Nutzerinformationen zu " + mitglied.getEffectiveName());
            bauplan.setDescription("Das ist der Member denn du aus gewählt hast!");
            bauplan.addField("ID des Nutzers", mitglied.getId(),true);

            Button kickButton = Button.secondary("kick" + mitglied.getId(),"Mitglied Kicken");
            Button banButton = Button.danger("ban" + mitglied.getId(), "Mitglied Bannen");
            Button profileButton = Button.link(mitglied.getEffectiveAvatarUrl(), "Profilbild abrufen");

            ereignis.getChannel().sendMessageEmbeds(bauplan.build()).setActionRow(kickButton, banButton, profileButton).queue();

        }

    }

    public void onButtonInteraction (ButtonInteractionEvent ereignis) {

        if (ereignis.getButton().getId().startsWith("kick")) {

            if (ereignis.getMember().getPermissions().contains(Permission.KICK_MEMBERS)) {

                String nutzerID = ereignis.getButton().getId().substring(4);

                ereignis.getGuild().kick(User.fromId(nutzerID)).queue();

                ereignis.reply("Bestrafung erfolgreich abgeschlossen!").queue();

            } else {

                ereignis.reply("Dir fehlen die rechte hierzu").queue();

            }

        } else if (ereignis.getButton().getId().startsWith("ban")) {

            if (ereignis.getMember().getPermissions().contains(Permission.BAN_MEMBERS)) {

                String bannen = ereignis.getButton().getId().substring(4);

                ereignis.getGuild().ban(User.fromId(bannen),7, TimeUnit.DAYS).reason("Bann eines Admins").queue();

            } else {

                ereignis.reply("Dir fehlen die rechte hierzu").queue();

            }

        }

    }

}
