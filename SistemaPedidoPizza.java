// Interface que define o contrato para as pizzas
interface Pizza {
    String getDescricao();
    double custo();
}

// Implementações das pizzas concretas
class Margherita implements Pizza {
    @Override
    public String getDescricao() {
        return "Pizza de Marguerita";
    }

    @Override
    public double custo() {
        return 8.99;
    }
}

class Pepperoni implements Pizza {
    @Override
    public String getDescricao() {
        return "Pizza de Pepperoni";
    }

    @Override
    public double custo() {
        return 10.99;
    }
}

// Padrão Decorator para adicionar ingredientes extras
abstract class DecoradorPizza implements Pizza {
    protected Pizza pizza;

    public DecoradorPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescricao() {
        return pizza.getDescricao();
    }

    @Override
    public double custo() {
        return pizza.custo();
    }
}

class DecoradorQueijoExtra extends DecoradorPizza {
    public DecoradorQueijoExtra(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + ", Pizza de Queijo Extra";
    }

    @Override
    public double custo() {
        return super.custo() + 2.50;
    }
}

// Padrão Bridge para criar diferentes tipos de pizzas com diferentes ingredientes
interface IngredientePizza {
    String getDescricaoIngrediente();
}

class MassaFina implements IngredientePizza {
    @Override
    public String getDescricaoIngrediente() {
        return "Massa Fina";
    }
}

class MassaEspessa implements IngredientePizza {
    @Override
    public String getDescricaoIngrediente() {
        return "Borda Espessa";
    }
}

class MassaRecheada implements IngredientePizza {
    @Override
    public String getDescricaoIngrediente() {
        return "Borda Recheada";
    }
}

// Adapter para criar pizzas a partir de ingredientes
class AdaptadorPizza implements Pizza {
    private IngredientePizza ingrediente;

    public AdaptadorPizza(IngredientePizza ingrediente) {
        this.ingrediente = ingrediente;
    }

    @Override
    public String getDescricao() {
        return ingrediente.getDescricaoIngrediente() + " Pizza";
    }

    @Override
    public double custo() {
        return 9.99; // Custo base
    }
}

// Classe principal para interação via linha de comando
public class SistemaPedidoPizza {
    public static void main(String[] args) {
        System.out.println("Bem-vindo ao sistema de pedidos de pizza!");

        // Exemplo de pedido de pizza com diferentes ingredientes e decorações
        Pizza pizza1 = new DecoradorQueijoExtra(new AdaptadorPizza(new MassaFina()));
        System.out.println("Pedido: " + pizza1.getDescricao());
        System.out.println("Custo: $" + pizza1.custo());

        Pizza pizza2 = new AdaptadorPizza(new MassaRecheada());
        System.out.println("\nPedido: " + pizza2.getDescricao());
        System.out.println("Custo: $" + pizza2.custo());
    }
}
