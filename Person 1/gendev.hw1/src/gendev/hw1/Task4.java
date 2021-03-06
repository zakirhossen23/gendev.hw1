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
	 * @Generate A
	 */
	 public void generateA() throws IOException
	   {
	       // it is very important that we do everything through ResourceSet's
	       ResourceSet resourceSet = new ResourceSetImpl();
	     
	       resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put( "ecore", new EcoreResourceFactoryImpl());
	       Resource resource = resourceSet.createResource(URI.createFileURI("model/hw1.ecore"));
	       EPackage newPackage = (EPackage)createPackage("PackageA", "PackageA", "http://PackageDB");;
	       resource.setURI(URI.createFileURI(instance));
	       // next, creating the 5 classes for 5 objects
	       newPackage.getEClassifiers().add(createEClass("Customer"));
	       newPackage.getEClassifiers().add(createEClass("Account"));
	       newPackage.getEClassifiers().add(createEClass("Finanace"));
	       newPackage.getEClassifiers().add(createEClass("Invest"));
	       newPackage.getEClassifiers().add(createEClass("Services"));
	       
	       // next, Adding into resource as EObject
	       resource.getContents().add(newPackage);	              
	       
	       Resource outputRes = resourceSet.createResource(URI.createFileURI(instance));
	       

	       // and at last, Saving...
	       resource.save( Collections.emptyMap());
	       System.out.println(resource.getURI().path() + " --- Saved");
	       
	   }

	
	
//	/**
//	 * @Generate B
//	 */
//	 public void generateB() throws IOException
//	   {
//	       // it is very important that we do everything through ResourceSet's
//	       ResourceSet resourceSet = new ResourceSetImpl();
//	     
//	       resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put( "ecore", new EcoreResourceFactoryImpl());
//	       Resource resource = resourceSet.createResource(URI.createFileURI("model/hw1.ecore"));
//	       EPackage newPackage = (EPackage)createPackage("Package", "Package", "http://PackageDB");;
//	       
//	       // next, create the row class
//	       EClass customerRow = createEClass("CustomerRow");
//	       // add to package before we do anything else
//	       newPackage.getEClassifiers().add(customerRow);
//	       // add our features
//	       addAttribute(customerRow, "id", EcorePackage.Literals.ESTRING, true, 1, 1);
//	       addAttribute(customerRow, "firstName", EcorePackage.Literals.ESTRING, false, 0, 1);
//	       addAttribute(customerRow, "lastName", EcorePackage.Literals.ESTRING, false, 0, 1);
//
//	       // now create a new resource to serialize the ecore model
//	      // Resource outputRes = resourceSet.getResource(null, false));
//	       // add our new package to resource contents
//	       resource.getContents().add(newPackage);
//	       Resource outputRes = resourceSet.createResource(URI.createFileURI(instance));
//	       System.out.println(resource.getURI().path());
//	       resource.setURI(URI.createFileURI(instance));
//	       System.out.println(resource.getURI().path());
//	       //outputRes.getContents().add(newPackage);
//	       
//	       // and at last, we save to standard out.  Remove the first argument to save to file specified in pathToOutputFile
//	       resource.save( Collections.emptyMap());
//	   }

	   private void addAttribute(EClass customerRow, String name,
	           EClassifier type, boolean isId, int lowerBound, int upperBound)
	   {
	       final EAttribute attribute = EcoreFactory.eINSTANCE.createEAttribute();
	       // always add to container first
	       customerRow.getEStructuralFeatures().add(attribute);
	       attribute.setName(name);
	       attribute.setEType(type);
	       attribute.setID(isId);
	       attribute.setLowerBound(lowerBound);
	       attribute.setUpperBound(upperBound);
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
	  
	   new Task4().generateA();
	}

}
