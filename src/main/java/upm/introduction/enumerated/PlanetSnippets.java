package upm.introduction.enumerated;

public class PlanetSnippets {
    public static void main(String[] args) {
        Planet planet = Planet.EARTH;
        System.out.println("Planeta: " + planet);
        System.out.println("Constante de gravitación: " + Planet.GRAVITATIONAL_CONSTANT);
        System.out.println("Masa: " + planet.mass());
        System.out.println("Radio: " + planet.radius());
        System.out.println("Gravedad de superficie: " + planet.surfaceGravity());
    }
}
