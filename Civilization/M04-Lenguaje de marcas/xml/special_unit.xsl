<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:param name="paramId"/>
    <xsl:variable name="copyrightSymbol">&#169;</xsl:variable>
    <xsl:template match="unit">
        <html>
            <head>
                <meta charset="UTF-8"/>
                <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
                <title>Civilizations</title>
                <link rel="stylesheet" href="special_unit.css"/>
            </head>
            <body>
                <nav class="navbar">
                    <ul class="nav-list">
                        <li class="nav-item">
                            <a href="index.html" class="nav-link home-icon">
                                <img src="imagenes/logo.png" class="icon"/>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="index.html" class="nav-link">Inicio</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle">Units and Buildings</a>
                            <ul class="dropdown-menu">
                                <li class="dropdown-item"><a href="attack_units.html" class="dropdown-link">Attack Units</a></li>
                                <li class="dropdown-item"><a href="defense_units.html" class="dropdown-link">Defense Units</a></li>
                                <li class="dropdown-item"><a href="special_units.html" class="dropdown-link">Special Units</a></li>
                                <li class="dropdown-item"><a href="buildings.html" class="dropdown-link">Buildings</a></li>
                            </ul>
                        </li>
                        <li class="nav-item">
                            <a href="tutorial.html" class="nav-link">Tutorial</a>
                        </li>
                        <li class="nav-item">
                            <a href="aboutus.html" class="nav-link">Sobre Nosotros</a>
                        </li>
                    </ul>
                </nav>
            
                <script src="menu.js"></script>
                <div class="content">
                    <div class="troop-name">
                        <h1><xsl:value-of select="name"/></h1>
                        <xsl:element name="img">
                            <xsl:attribute name="class">troop-sprite</xsl:attribute>
                            <xsl:attribute name="src">imagenes/<xsl:value-of select="sprite"/>.png</xsl:attribute>
                        </xsl:element>
                    </div>
                    <br></br>
                    <h2> Estadisticas Básicas </h2>
                    <table>
                        <thead>
                            <tr>
                                <th><img class="sprite" src="imagenes/damage.png"/> Daño base</th>
                                <th><img class="sprite" src="imagenes/waste_chance.png"/> Probabilidad restos</th>
                                <th><img class="sprite" src="imagenes/attack_again.png"/> Probabilidad volver a atacar</th>
                                <th><img class="sprite" src="imagenes/sanct.png"/> Santificable</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><xsl:value-of select="base_damage"></xsl:value-of></td>
                                <td><xsl:value-of select="waste_chance"></xsl:value-of></td>
                                <td><xsl:value-of select="attack_again_chance"></xsl:value-of></td>
                                <td>No</td>
                            </tr>
                        </tbody>
                    </table>
                    <br></br>
                    <h2> Estadisticas Plus </h2>
                    <table>
                        <thead>
                            <tr>
                                <th>Tecnologia de armadura</th>
                                <th>Tecnologia de ataque</th>
                                <th>Experiencia de armadura</th>
                                <th>Experiencia de ataque</th>
                                <th>Santificacion de armadura</th>
                                <th>Santificacion de ataque</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><xsl:value-of select="plus_stats/armour_technology"></xsl:value-of></td>
                                <td><xsl:value-of select="plus_stats/attack_technology"></xsl:value-of></td>
                                <td><xsl:value-of select="plus_stats/armour_experience"></xsl:value-of></td>
                                <td><xsl:value-of select="plus_stats/attack_experience"></xsl:value-of></td>
                                <td><xsl:value-of select="plus_stats/armour_sanctified"></xsl:value-of></td>
                                <td><xsl:value-of select="plus_stats/attack_sanctified"></xsl:value-of></td>
                            </tr>
                        </tbody>
                    </table>
                    <br></br>
                    <h2> Costes </h2>
                    <table>
                        <thead>
                            <tr>
                                <th><img class="sprite" src="imagenes/naranja.png"/>Coste de comida</th>
                                <th><img class="sprite" src="imagenes/maderas.png"/>Coste de madera</th>
                                <th><img class="sprite" src="imagenes/minerals.png"/>Coste de hierro</th>
                                <th><img class="sprite" src="imagenes/matraz.png"/>Coste de mana</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><xsl:value-of select="costs/food_cost"></xsl:value-of></td>
                                <td><xsl:value-of select="costs/wood_cost"></xsl:value-of></td>
                                <td><xsl:value-of select="costs/iron_cost"></xsl:value-of></td>
                                <td><xsl:value-of select="costs/mana_cost"></xsl:value-of></td>
                            </tr>
                        </tbody>
                    </table>

                    <br></br>
                    <a href="special_units.html" class="back-link">Volver Atrás</a>
                </div>
                <footer class="footer">
                    <div class="footer-content">
                        <p><xsl:value-of select="$copyrightSymbol"/> 2024 Civilizations. Todos los derechos reservados.</p>
                    </div>
                </footer>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>