package main;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

public class VerifySystem extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent ereignis) {

        if (ereignis.getMessage().getContentStripped().equals("!setup verify")) {

            if (ereignis.getMember().getPermissions().contains(Permission.ADMINISTRATOR)) {

                EmbedBuilder embed = getEmbedBuilder();

                Button button = Button.success("verify", "✅ Verifiziere dich hier!");

                ereignis.getChannel().sendMessageEmbeds(embed.build()).setActionRow(button).queue();


            }

        }

    }

    @NotNull
    private static EmbedBuilder getEmbedBuilder() {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Verifiziere dich hier!");
        embed.setThumbnail("https://cdn.discordapp.com/attachments/1136652506219417640/1150449130380984400/dcmoji_5922-verified-icon.png");
        embed.setColor(0x42b580);
        embed.setDescription("Verhalte dich immer höflich und respektvoll gegenüber den anderen Nutzern!");
        embed.addField(" ❓ Wie kann ich mich verifizieren?", "klicke auf den button unter dieser Nachricht!", true);
        return embed;
    }


    public void onButtonInteraction (ButtonInteractionEvent ereignis) {

        if (ereignis.getButton().getId().equals("verify")) {

            Role verifyRole = ereignis.getGuild().getRoleById("1141319424511594526");

            ereignis.getGuild().addRoleToMember(ereignis.getMember(), verifyRole).queue();

            ereignis.reply("Du hast dich Erfolgreich Verifiziert!").setEphemeral(true).queue();

        }

    }

}
