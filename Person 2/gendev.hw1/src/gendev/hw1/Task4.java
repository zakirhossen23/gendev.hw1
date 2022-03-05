package gendev.hw1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.xml.crypto.Data;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

public class Task4 {

	public static String instance = "task4_instance/instance.xmi";

	/**
	 * @Generate Instance
	 */
	 public void generateInstance() throws IOException
	   {
	       // it is very important that we do everything through ResourceSet's
	       ResourceSet resourceSet = new ResourceSetImpl();
	     
	       resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put( "ecore", new EcoreResourceFactoryImpl());
	       Resource resource = resourceSet.createResource(URI.createFileURI("model/hw1.ecore"));
	       EPackage newPackage = (EPackage)createPackage("InstancePackage", "InstancePackage", "http://PackageDB");;
	       resource.setURI(URI.createFileURI(instance));
	       // next, creating  classes for objects
	       newPackage.getEClassifiers().add(createEClass("Person"));
	       newPackage.getEClassifiers().add(createEClass("AllServices"));
	       newPackage.getEClassifiers().add(createEClass("MonthlyPlan"));
	       newPackage.getEClassifiers().add(createEClass("FreeVersion"));
	       newPackage.getEClassifiers().add(createEClass("BuyMonthly"));
	       newPackage.getEClassifiers().add(createEClass("BuyYearly"));
	       newPackage.getEClassifiers().add(createEClass("YearlyPlan"));
	       // next, Adding into resource as EObject
	       resource.getContents().add(newPackage);	              
	       
	       Resource outputRes = resourceSet.createResource(URI.createFileURI(instance));
	       

	       // and at last, Saving...
	       resource.save( Collections.emptyMap());
	       System.out.println(resource.getURI().path() + " --- Saved");
	       
	   }

	


	   private EPackage createPackage(final String name, final String prefix,
	           final String uri)
	   {
	       final EPackage epackage = EcoreFactory.eINSTANCE.createEPackage();
	       epackage.setName(name);
	       epackage.setNsPrefix(prefix);
	       epackage.setNsURI(uri);
	       return epackage;
	   }
	   
	   private EClass createEClass(final String name)
	   {
	       final EClass eClass = EcoreFactory.eINSTANCE.createEClass();
	       eClass.setName(name);
	       return eClass;
	   }

	
	
	public static void main(String[] args) throws IOException {
		System.out.println("Creating and saving instance to file " + instance);
	    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
	  
	   new Task4().generateInstance();
	}

}
