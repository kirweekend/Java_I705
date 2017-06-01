package hw4;

import homework4.TextIO;
import homework4.entity.Categorie;
import homework4.entity.NewProduct;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {

	static EntityManager em;

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("categories");
		em = entityManagerFactory.createEntityManager();

		while (true) {
			System.out.println("\nPlease choose:");
			System.out.println("1 - list new products");
			System.out.println("2 - add new product");
			System.out.println("3 - remove new product");
			System.out.println("4 - search");
			System.out.println("5 - any other number to exit");

			int selection = TextIO.getlnInt();

			em.getTransaction().begin();

			switch (selection) {
			case 1:
				listNewProducts();
				break;
			case 2:
				addNewProduct();
				break;
			case 3:
				removeNewProduct();
				break;
			case 4:
				searchProduct();
				break;
			default:
				em.close();
				return;
			}

			em.getTransaction().commit();
		}
	}


	public static void listNewProducts() {
		List<NewProduct> newProducts = em.createQuery("from NewProduct", NewProduct.class).getResultList();
		for (NewProduct product : newProducts) {
			System.out.println(product);
		}
		System.out.println();
	}

	public static void addNewProduct() {
		int update = 0;

		System.out.println("What is the name of new Product?");
		String name = TextIO.getlnString();

		TypedQuery<NewProduct> query = em.createQuery("from NewProduct where name like(:name)", NewProduct.class);

		query.setParameter("name", name);
		query.setMaxResults(1);
		NewProduct newProduct = null;

		try {
			newProduct = query.getSingleResult();
			System.out.println("Do you which to alter pre-existing new Product (y/n): " + newProduct);
			String input = TextIO.getlnString().toLowerCase();
			if (input.charAt(0) != 'y') {
				return;
			} else {
				update = 1;
			}
		} catch (NoResultException e) {
		}

		if (newProduct == null) {
			newProduct = new NewProduct();
			newProduct.setName(name);
		}


	public static void removeNewProduct() {
		System.out.println("What is the name of new Product you wish to remove?");
		String name = TextIO.getlnString();

		System.out.println("Do you wish to delete this Product (y/n): " + name);
		String input = TextIO.getlnString().toLowerCase();
		if (input.charAt(0) != 'y') {
			return;
		}
		TypedQuery<NewProduct> query = em.createQuery("SELECT n FROM NewProduct n WHERE name = :name", NewProduct.class);
		query.setParameter("name", name);
		query.setMaxResults(1);
		NewProduct result = query.getSingleResult();

		Query query3 = em.createQuery("DELETE FROM NewProduct WHERE name = :name");
		query3.setParameter("name", name);
		query3.executeUpdate();

	}

	  public static void searchProduct(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter keywords :");
        String line = scanner.nextLine();
        String[] keywords = line.split(" ");

        System.out.println("Finding products match with keywords" + line);
        String queryString = "FROM NewProduct WHERE";

        for (int i = 0; i < keywords.length; i++) {
            if(i==0){
                queryString += " (LOWER(name) LIKE LOWER(:keyword" + i + "))";
            }else {
                queryString += " AND (LOWER(name) LIKE LOWER(:keyword" + i + "))";
            }
        }
        TypedQuery<NewProduct> query = em.createQuery(queryString, NewProduct.class);
        for (int i = 0; i < keywords.length; i++) {
            query.setParameter("keyword" + i, "%" + keywords[i] + "%");

        }

        List<NewProduct> result = query.getResultList();

        if(result != null){
            for (NewProduct product: result) {
                System.out.println("================================");
                System.out.println("Product: " + product.getName());
                System.out.println("=================================");
            }
        } else {
            System.out.println("Nothing found");
        }

        em.close();
        em.getTransaction().commit();

    }
}
