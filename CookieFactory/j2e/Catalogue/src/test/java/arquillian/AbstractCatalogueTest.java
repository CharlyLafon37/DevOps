package arquillian;

import fr.unice.polytech.isa.tcf.CartModifier;
import fr.unice.polytech.isa.tcf.CatalogueExploration;
import fr.unice.polytech.isa.tcf.asynchronous.KitchenPrinter;
import fr.unice.polytech.isa.tcf.components.CartBean;
import fr.unice.polytech.isa.tcf.components.CatalogueBean;
import fr.unice.polytech.isa.tcf.components.carts.CartStatefulBean;
import fr.unice.polytech.isa.tcf.entities.Customer;
import fr.unice.polytech.isa.tcf.utils.Database;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import javax.ejb.EJB;

public abstract class AbstractCatalogueTest {


	@EJB
	protected Database memory;

	@Deployment
	public static WebArchive createDeployment() {
		// Building a Web ARchive (WAR) containing the following elements:
		return ShrinkWrap.create(WebArchive.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addPackage(CatalogueBean.class.getPackage())
				.addPackage(CatalogueExploration.class.getPackage())
				.addAsManifestResource(new ClassLoaderAsset("META-INF/persistence.xml"), "persistence.xml");

	}

}
