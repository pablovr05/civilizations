<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:param name="paramId"/>
    <xsl:template match="/attack_units">
        <html>
            <head>
                <meta charset="UTF-8"/>
                <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
                <title>Div con Hover</title>
                <link rel="stylesheet" href="attack_units.css"/>
            </head>
            <body>
                <div class="hover-div">
                    <p>SWORDSMAN</p>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>