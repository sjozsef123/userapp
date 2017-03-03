import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import solomonj.msg.userapp.jpa.model.Article;
import solomonj.msg.userapp.jpa.model.Author;
import solomonj.msg.userapp.jpa.model.Book;
import solomonj.msg.userapp.jpa.model.Magazine;
import solomonj.msg.userapp.jpa.model.Newspaper;
import solomonj.msg.userapp.jpa.util.JaxB;

public class JaxBTest {

	@Test
	public void test() {
		
		Book book = new Book();
		book.setTitle("book");
		List<Author> bauthors = new ArrayList<>();
		bauthors.add(new Author(1, "name1"));
		bauthors.add(new Author(2, "name2"));
		book.setbAuthors(bauthors);
		
		Magazine magazine = new Magazine();
		magazine.setTitle("magazine");
		List<Author> mauthors = new ArrayList<>();
		mauthors.add(new Author(1, "name1"));
		mauthors.add(new Author(2, "name2"));
		magazine.setmAuthors(mauthors);
		
		Newspaper newspaper = new Newspaper();
		newspaper.setTitle("newspaper");
		List<Article> articles = new ArrayList<>();
		articles.add(new Article(1, "art1"));
		articles.add(new Article(2, "art2"));
		newspaper.setArticles(articles);
		
		JaxB.marshall(newspaper);
		
	}
	
}
