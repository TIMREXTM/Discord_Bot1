package main;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.PermissionOverride;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumSet;

public class TicketSystem extends ListenerAdapter {

    public void onMessageReceived (MessageReceivedEvent event) {

        if (event.getMessage().getContentStripped().equals("!setup ticket")) {

            String roles = String.valueOf(event.getMember().getRoles());

            if (roles.contains("Admin")) {

                EmbedBuilder embed = new EmbedBuilder();

                embed.setColor(Color.green);
                embed.setTitle("Ticket System");
                embed.setDescription("Klicke auf den knopf um ein Ticket zu öffnen");

                event.getChannel().sendMessageEmbeds(embed.build()).setActionRow(Button.success("openTicket", "Ticket öffnen").withEmoji(Emoji.fromFormatted("🎫"))).queue();

            }

        }

    }

    public void onButtonInteraction (ButtonInteractionEvent event) {

        event.deferEdit().queue();

        if (event.getButton().getId().equals("openTicket")) {

            String roles = String.valueOf(event.getMember().getRoles());

            if (!roles.contains("Ticketspere")) {
                int min = 1000;
                int max = 99999;
                int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
                SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                Date date = new Date();

                Guild guild = event.getGuild();

                EmbedBuilder embed = new EmbedBuilder();

                embed.setColor(Color.green);
                embed.setTitle(event.getUser().getName() + " Ticket");
                embed.setDescription(" <@&1160506298949976204> Ein Team mitglied wird sich bald um sie kümmern sie können ja so lange den grund ihres Tickets eingeben");

                guild.createTextChannel("ticket" + random_int, guild.getCategoryById("1140677037695193240"))
                        .addPermissionOverride(event.getMember(), EnumSet.of(Permission.VIEW_CHANNEL), null)
                        .addPermissionOverride(guild.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                        .addPermissionOverride(guild.getRoleById("1141470403018117120"), EnumSet.of(Permission.VIEW_CHANNEL), null)
                        .complete().sendMessageEmbeds(embed.build()).setActionRow(closeButton(), claimButton()).queue();

                EmbedBuilder embedTeam = new EmbedBuilder();
                embedTeam.setColor(Color.green);
                embedTeam.setTitle("Ticket System");
                embedTeam.addField("Nutzer", event.getMember().getAsMention(),true);
                embedTeam.addField("Datum",formatter.format(date) ,true);

                guild.getTextChannelById("1136302510844424404").sendMessageEmbeds(embedTeam.build()).queue();

            } else {

                EmbedBuilder embed = new EmbedBuilder()
                        .setColor(Color.green)
                        .setTitle("Ticket System")
                        .setDescription("Du hast eine Ticketspere!");

                event.getUser().openPrivateChannel().complete().sendMessageEmbeds(embed.build()).queue();

            }

        } else if (event.getButton().getId().equals("closeButton")) {

            String roles = String.valueOf(event.getMember().getRoles());

            if (roles.contains("Admin") || roles.contains("Support")) {

                event.getInteraction().getChannel().delete().queue();

            }

        } else if (event.getButton().getId().equals("claimButton")) {

            if (event.getInteraction().getMember().hasPermission(Permission.KICK_MEMBERS)) {
                TextChannel channel = event.getChannel().asTextChannel();

                event.getInteraction().getMessage().delete().queue();

                EmbedBuilder embed = new EmbedBuilder()
                        .setColor(Color.green)
                        .setTitle("Ticket System")
                        .setDescription(event.getInteraction().getUser().getAsMention() + " wird sich um sie kümmern");

                channel.sendMessageEmbeds(embed.build()).setActionRow(closeButton()).queue();
                PermissionOverride permissionOverride = channel.getPermissionOverride(event.getMember());


            }


        }

    }

    private Button closeButton() {

        return Button.danger("closeButton", "Schließen");

    }

    private Button claimButton() {

        return Button.success("claimButton", "Claimer");

    }

}
