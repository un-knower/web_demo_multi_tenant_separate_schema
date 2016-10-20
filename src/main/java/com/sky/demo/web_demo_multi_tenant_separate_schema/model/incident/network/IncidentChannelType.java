/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.skyguard.sps.base.common.locale.Localizer
 */
package com.sky.demo.web_demo_multi_tenant_separate_schema.model.incident.network;

import com.google.common.collect.ImmutableList;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.Localizer;

import java.util.List;

public enum IncidentChannelType {
    
    HTTP(1, "incident_channel_http"),
    HTTPS(2, "incident_channel_https"),
    FTP(3, "incident_channel_ftp"),
    IM(4, "incident_channel_im"),
    EMAIL(5, "incident_channel_email"),
    CUSTOM(6, "incident_channel_custom"),
    NETWORK_PRINTING(8, "incident_channel_network_printing"),
    IMAP(9, "incident_channel_imap"),
    POP3(10, "incident_channel_pop3"),
    ENDPOINT_HTTP(11, "incident_channel_endpoint_http"),
    ENDPOINT_HTTPS(12, "incident_channel_endpoint_https"),
    ENDPOINT_FTP(13, "incident_channel_endpoint_ftp"),
    ENDPOINT_IM(14, "incident_channel_endpoint_im"),
    ENDPOINT_EMAIL(15, "incident_channel_endpoint_email"),
    ENDPOINT_CUSTOM(16, "incident_channel_endpoint_custom"),
    ENDPOINT_PRINTING(18, "incident_channel_endpoint_printing"),
    ENDPOINT_REMOVABLE_MEDIA(19, "incident_channel_endpoint_removable_media"),
    ENDPOINT_LAN(20, "incident_channel_endpoint_lan"),
    ENDPOINT_CD_BURNER(21, "incident_channel_endpoint_cd_burner"),
    ENDPOINT_APPLICATION(22, "incident_channel_endpoint_application"),

    ENDPOINT_APPLICATION_COPY_OR_CUT(100, "incident_channel_endpoint_application_copy_or_cut"),
    ENDPOINT_APPLICATION_PASTE(101, "incident_channel_endpoint_application_paste"),
    ENDPOINT_APPLICATION_FILE_ACCESS(102, "incident_channel_endpoint_application_file_access"),
    ENDPOINT_APPLICATION_SCREEN_CAPTURE(103, "incident_channel_endpoint_application_screen_capture");
    
    public static final List<IncidentChannelType> ALL_NETWORK_INCIDENT_CHANNEL_TYPES;
    public static final List<IncidentChannelType> ALL_ENDPOINT_INCIDENT_CHANNEL_TYPES;
    static {
        ALL_NETWORK_INCIDENT_CHANNEL_TYPES = ImmutableList.of((HTTP), (HTTPS), (FTP), (IM), (EMAIL), (CUSTOM), (NETWORK_PRINTING), (IMAP), (POP3));
        ALL_ENDPOINT_INCIDENT_CHANNEL_TYPES = ImmutableList.of((ENDPOINT_HTTP), (ENDPOINT_HTTPS), (ENDPOINT_FTP), (ENDPOINT_IM), (ENDPOINT_EMAIL), (ENDPOINT_CUSTOM), (ENDPOINT_PRINTING), (ENDPOINT_REMOVABLE_MEDIA), (ENDPOINT_LAN), (ENDPOINT_CD_BURNER), (ENDPOINT_APPLICATION_COPY_OR_CUT), (ENDPOINT_APPLICATION_PASTE), new IncidentChannelType[]{ENDPOINT_APPLICATION_FILE_ACCESS, ENDPOINT_APPLICATION_SCREEN_CAPTURE});
    }
    
    
    private int id;
    private String value;

    private IncidentChannelType(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public static IncidentChannelType get(int id) {
        for (IncidentChannelType channelType : IncidentChannelType.values()) {
            if (channelType.getId() != id) continue;
            return channelType;
        }
        return null;
    }

    public String getDisplayName() {
        return Localizer.getMessage((String) this.getValue());
    }

    public int getId() {
        return this.id;
    }

    public String getValue() {
        return this.value;
    }

    
}

