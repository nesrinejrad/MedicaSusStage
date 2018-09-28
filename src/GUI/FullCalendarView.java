package tn.MedicaSud.app.client.gui;

import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import tn.MedicaSud.entities.Intervention;
import tn.MedicaSud.services.InterventionServicesRemote;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.scene.layout.BorderPane;


public class FullCalendarView {

    private ArrayList<AnchorPaneNode> allCalendarDays = new ArrayList<>(35);
    private VBox view;
    private Text calendarTitle;
    private YearMonth currentYearMonth;
    private List< LocalDate> taDates= new ArrayList<LocalDate>();
    private List< LocalDate> DatesSansPeriode= new ArrayList<LocalDate>();
    private List<Intervention> interventions= new ArrayList<Intervention>();
    private List<Integer> periodes= new ArrayList<Integer>();
   private List<LocalDate> DateSuivant= new ArrayList<LocalDate>();
   private List<LocalDate> DatePrecedent= new ArrayList<LocalDate>();
   private List<String> descriptions= new ArrayList<String>();
   private List<String> descriptionsSansPeriode= new ArrayList<String>();
   Utilites utilities= new Utilites();

    /**
     * Create a calendar view
     * @param yearMonth year month to create the calendar of
     * @throws NamingException 
     */
    public FullCalendarView(YearMonth yearMonth)  {
    	try {
			utilities.context= new InitialContext();
			utilities.interventionServicesRemote= (InterventionServicesRemote) utilities.context.lookup(utilities.interventionRemote);
			interventions=utilities.interventionServicesRemote.findAll();
			taDates.clear();
			for (Intervention intervention : interventions) {
				if(intervention.getPeriode()!=0)
				{taDates.add(intervention.getDateIntervention());
				periodes.add(intervention.getPeriode());
				descriptions.add(intervention.getDescription());}
				else
				{
					DatesSansPeriode.add(intervention.getDateIntervention());
					descriptionsSansPeriode.add(intervention.getDescription());}
				}

			
		
	     
		} catch (NamingException e1) {
		
		}

        
        currentYearMonth = yearMonth;
        DatePrecedent.addAll(taDates);
        // Create the calendar grid pane
        GridPane calendar = new GridPane();
        calendar.setPrefSize(600, 400);
        calendar.setGridLinesVisible(true);
        // Create rows and columns with anchor panes for the calendar
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                AnchorPaneNode ap = new AnchorPaneNode();
                ap.getChildren().clear();
                ap.setPrefSize(200,200);
                calendar.add(ap,j,i);
                allCalendarDays.add(ap);
            }
        }
        // Days of the week labels
        Text[] dayNames = new Text[]{ new Text("Sunday"), new Text("Monday"), new Text("Tuesday"),
                                        new Text("Wednesday"), new Text("Thursday"), new Text("Friday"),
                                        new Text("Saturday") };
        GridPane dayLabels = new GridPane();
        dayLabels.setPrefWidth(600);
        Integer col = 0;
        for (Text txt : dayNames) {
            AnchorPane ap = new AnchorPane();
            ap.setPrefSize(200, 10);
            ap.setBottomAnchor(txt, 5.0);
            ap.getChildren().add(txt);
            dayLabels.add(ap, col++, 0);
        }
        // Create calendarTitle and buttons to change current month
        calendarTitle = new Text();
        Button previousMonth = new Button("<<");
        previousMonth.setOnAction(e -> previousMonth());
        Button nextMonth = new Button(">>");
        nextMonth.setOnAction(e -> nextMonth());
        HBox titleBar = new HBox(previousMonth, calendarTitle, nextMonth);
        titleBar.setAlignment(Pos.BASELINE_CENTER);
        // Populate calendar with the appropriate day numbers
        populateCalendar(yearMonth);
        // Create the calendar view
           BorderPane borderPane= new BorderPane();
        view = new VBox(titleBar, dayLabels, calendar);
       
    }

    /**
     * Set the days of the calendar to correspond to the appropriate date
     * @param yearMonth year and month of month to render
     */
    public void populateCalendar(YearMonth yearMonth) {
    	 LocalDate ld1;
         
         // Get the date we want to start with on the calendar
         LocalDate calendarDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
         // Dial back the day until it is SUNDAY (unless the month starts on a sunday)
         while (!calendarDate.getDayOfWeek().toString().equals("SUNDAY") ) {
             calendarDate = calendarDate.minusDays(1);
         }
         // Populate the calendar with day numbers
         List<LocalDate> Dates= new ArrayList<LocalDate>();
         List<String> descriptions2= new ArrayList<String>();

         DateSuivant.clear();
         List<Integer> periode2= new ArrayList<Integer>();
         for (int i=0; i < DatesSansPeriode.size();i++)
         {
        	 
         }
          for (int i=0; i < periodes.size();i++)
          {       
                     LocalDate ld= DatePrecedent.get(i);
                     Dates.add(ld);
                     if(ld.getMonthValue()!=currentYearMonth.getMonthValue()  )
                     { 
                         DateSuivant.add(ld);
                     }
                 Integer nbJour= ld.getDayOfMonth();
                 nbJour=ld.getDayOfYear();
                 while(((nbJour=nbJour+periodes.get(i))<365)&& (((LocalDate.ofYearDay(ld.getYear(), nbJour)).getMonthValue())==(currentYearMonth.getMonthValue())))
                 {      
                      {ld1= LocalDate.ofYearDay(ld.getYear(),nbJour);
                      Dates.add(ld1);
                      periode2.add(periodes.get(i));
                      descriptions2.add(descriptions.get(i));}          
                  }          
                 if(nbJour<365)
                 {
                      ld1= LocalDate.ofYearDay(ld.getYear(),nbJour);
                 }
                 else
                 {
                     nbJour=nbJour-365;
                     Integer year= ld.getYear()+1;
                     System.out.println(year);
                     ld1= LocalDate.ofYearDay(year,nbJour);
                 }
                 if(ld.getMonthValue()<currentYearMonth.getMonthValue() +1 )
                 {periode2.add(periodes.get(i));
                 DateSuivant.add(ld1);
                 descriptions2.add(descriptions.get(i));}
                 System.out.println("date suivant="+DateSuivant.toString());
                 System.out.println("periode2="+periode2.toString());
          }

                    int i=0;
                    int j=0;

         for (AnchorPaneNode ap : allCalendarDays) {

             if (ap.getChildren().size() != 0) {
               //  ap.getChildren().remove(0);
                 ap.getChildren().clear();
             }
             Text txt = new Text(String.valueOf(calendarDate.getDayOfMonth()));
             ap.setDate(calendarDate);
          for(LocalDate ld :Dates)
              
             {   if(calendarDate.equals(ld))
             { 
                  Text text=new Text(descriptions2.get(i));
                  text.setFill(Color.BLUE);
                  ap.setTopAnchor(text,13.0);
                  ap.setLeftAnchor(text, 13.0);
                  ap.getChildren().add(text);
                  i++;
                  }
                 
             }
          for(LocalDate ld :DatesSansPeriode)
              
          {  
        	  if(calendarDate.equals(ld))
          { 
               Text text=new Text(descriptionsSansPeriode.get(j));
               text.setFill(Color.BLUE);
               ap.setTopAnchor(text,13.0);
               ap.setLeftAnchor(text, 13.0);
               ap.getChildren().add(text);
               j++;
               }
              
          }
             ap.setTopAnchor(txt, 5.0);
             ap.setLeftAnchor(txt, 5.0);
             ap.getChildren().add(txt);
             calendarDate = calendarDate.plusDays(1);

         }
             Dates.clear();
            taDates.clear();
            taDates.addAll(DatePrecedent);
             DatePrecedent.clear();
             DatePrecedent.addAll(DateSuivant);
         // Change the title of the calendar
         calendarTitle.setText(yearMonth.getMonth().toString() + " " + String.valueOf(yearMonth.getYear()));
     }

       public void populateCalendarNext(YearMonth yearMonth) {
    	    LocalDate ld1;
    	        DatePrecedent.clear();
    	       // taDates.addAll(DateSuivant);
    	        DateSuivant.clear();
    	         List<String> descriptions2= new ArrayList<String>();

    	        DateSuivant.addAll(taDates);
    	        // Get the date we want to start with on the calendar
    	        LocalDate calendarDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
    	        // Dial back the day until it is SUNDAY (unless the month starts on a sunday)
    	        while (!calendarDate.getDayOfWeek().toString().equals("SUNDAY") ) {
    	            calendarDate = calendarDate.minusDays(1);
    	        }
    	        // Populate the calendar with day numbers
    	        List<LocalDate> Dates= new ArrayList<LocalDate>();
    	        List<Integer> periode2= new ArrayList<Integer>();
    	          Dates.clear();
    	         for (int i=0; i < periodes.size();i++)
    	         {  
    	                    LocalDate ld= taDates.get(i);
    	                      if(ld.getMonthValue()<currentYearMonth.getMonthValue())
    	                    {
    	                        DatePrecedent.add(ld);
    	                    }
    	                Integer nbJour= ld.getDayOfMonth();
    	                nbJour=ld.getDayOfYear();
    	                while(((nbJour=nbJour-periodes.get(i))>0)&& (LocalDate.ofYearDay(ld.getYear(), nbJour).getMonthValue()==(currentYearMonth.getMonthValue())))
    	                {   
    	                     ld1= LocalDate.ofYearDay(ld.getYear(),nbJour);
    	                     
    	                     Dates.add(ld1);
    	                     periode2.add(periodes.get(i));
    	                      descriptions2.add(descriptions.get(i));      

    	                     
    	                 }           
    	                if(nbJour>0)
    	                {
    	                     ld1= LocalDate.ofYearDay(ld.getYear(),nbJour);
    	                }
    	                else
    	                {
    	                    nbJour=365+nbJour;
    	                    ld1= LocalDate.ofYearDay(ld.getYear()-1,nbJour);
    	                      descriptions2.add(descriptions.get(i));       

    	                }
    	                
    	                periode2.add(periodes.get(i));
    	                DatePrecedent.add(ld1);
    	         }
    	            
    	         int i=0;
    	         int j=0;
    	        for (AnchorPaneNode ap : allCalendarDays) {

    	            if (ap.getChildren().size() != 0) {
    	              //  ap.getChildren().remove(0);
    	                ap.getChildren().clear();
    	            }
    	            Text txt = new Text(String.valueOf(calendarDate.getDayOfMonth()));
    	            ap.setDate(calendarDate);
    	         
    	                   
    	         for(LocalDate ld :Dates)
    	             
    	            {   if(calendarDate.equals(ld))
    	            {   Text text=new Text(descriptions2.get(i));
    	                 ap.setTopAnchor(text,13.0);
    	                 ap.setLeftAnchor(text, 13.0);
    	                 ap.getChildren().add(text);
    	                 i++;
    	                 }
    	                
    	            }
    	         for(LocalDate ld :DatesSansPeriode)
    	              
    	          {  
    	        	  if(calendarDate.equals(ld))
    	          { 
    	               Text text=new Text(descriptionsSansPeriode.get(j));
    	               text.setFill(Color.BLUE);
    	               ap.setTopAnchor(text,13.0);
    	               ap.setLeftAnchor(text, 13.0);
    	               ap.getChildren().add(text);
    	               j++;
    	               }
    	              
    	          }
    	            ap.setTopAnchor(txt, 5.0);
    	            ap.setLeftAnchor(txt, 5.0);
    	            ap.getChildren().add(txt);
    	            calendarDate = calendarDate.plusDays(1);
    	        }
    	            Dates.clear();
    	                   taDates.clear();
    	                   taDates.addAll(DatePrecedent);
    	                   DatePrecedent.clear();
    	                   DatePrecedent.addAll(DateSuivant);
    	    	            System.out.println("taDAte  precedent fele5er="+taDates.toString());
    	            System.out.println("date suivant precedent fele5er="+DateSuivant.toString());
    	            System.out.println("date precedent precedent fele5er="+DatePrecedent.toString());

    	        calendarTitle.setText(yearMonth.getMonth().toString() + " " + String.valueOf(yearMonth.getYear()));
    	        System.out.println("********************");
       }
     /**
      * Move the month back by one. Repopulate the calendar with the correct dates.
      */
     private void previousMonth() {
         currentYearMonth = currentYearMonth.minusMonths(1);
         populateCalendarNext(currentYearMonth);
         
     }

     /**
      * Move the month forward by one. Repopulate the calendar with the correct dates.
      */
     private void nextMonth() {
         currentYearMonth = currentYearMonth.plusMonths(1);
         populateCalendar(currentYearMonth);
     }

     public VBox getView() {
         return view;
     }

     public ArrayList<AnchorPaneNode> getAllCalendarDays() {
         return allCalendarDays;
     }

     public void setAllCalendarDays(ArrayList<AnchorPaneNode> allCalendarDays) {
         this.allCalendarDays = allCalendarDays;
     }
 }