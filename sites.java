

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import se.sics.isl.transport.Transportable;
import se.sics.tasim.aw.Agent;
import se.sics.tasim.aw.Message;
import se.sics.tasim.props.SimulationStatus;
import se.sics.tasim.props.StartInfo;
import tau.tac.adx.ads.properties.AdType;
import tau.tac.adx.demand.CampaignStats;
import tau.tac.adx.devices.Device;
import tau.tac.adx.props.AdxBidBundle;
import tau.tac.adx.props.AdxQuery;
import tau.tac.adx.props.PublisherCatalog;
import tau.tac.adx.props.PublisherCatalogEntry;
import tau.tac.adx.props.ReservePriceInfo;
import tau.tac.adx.report.adn.AdNetworkReport;
import tau.tac.adx.report.adn.MarketSegment;
import tau.tac.adx.report.demand.AdNetBidMessage;
import tau.tac.adx.report.demand.AdNetworkDailyNotification;
import tau.tac.adx.report.demand.CampaignOpportunityMessage;
import tau.tac.adx.report.demand.CampaignReport;
import tau.tac.adx.report.demand.CampaignReportKey;
import tau.tac.adx.report.demand.InitialCampaignMessage;
import tau.tac.adx.report.demand.campaign.auction.CampaignAuctionReport;
import tau.tac.adx.report.publisher.AdxPublisherReport;
import tau.tac.adx.report.publisher.AdxPublisherReportEntry;
import edu.umich.eecs.tac.props.Ad;
import edu.umich.eecs.tac.props.BankStatus;


private class SitesStatistic
{
	Class constSiteStatistics
	{
		String name;
		int youngStat; //age 44 and below
		int oldStat; //age 45 and above
		int lowIncomeStat; //below 60
		int highIncomeStat; //above 60
		int maleStat;
		int femaleStat;
		int mobileStat;
		int popularity;

		public constSiteStatistics(String name, int youngStat, int oldStat,int lowIncomeStat, int highIncomeStat, int maleStat, int femaleStat, int mobileStat, int popularity)
		{
			this.name=name;
			this.youngStat=youngStat; 
			this.oldStat=oldStat; 	  
			this.highIncomeStat=highIncomeStat; 
			this.lowIncomeStat = lowIncomeStat; 
			this.maleStat=maleStat;
			this.femaleStat = femaleStat;
			this.mobileStat = mobileStat;
			this.popularity = popularity;
		}
		public String getName()
		{
			return this.name;
		}
		public int getYoungStat()
		{
			return this.youngStat;
		}
		public int getOldStat()
		{
			return this.oldStat;
		}
		public int getHighIncomeStat()
		{
			return this.highIncomeStat;
		}
		public int getLowIncomeStat()
		{
			return this.lowIncomeStat;
		}
		public int getMaleStat()
		{
			return this.maleStat;
		}
		public int getFemaleStat()
		{
			return this.femaleStat;
		}
		public int getMobileStat()
		{
			return this.mobileStat;
		}
		public int getPopularity()
		{
			return this.popularity;
		}
	}



	constSiteStatistics[] newsSites;
	constSiteStatistics[] shoppingSites;
	constSiteStatistics[] webInformationSites;

	public SitesStatistic()
	{

		constSiteStatistics yahoo=new constSiteStatistics("Yahoo",12.2+17.1+16.7,18.4+16.4,53+27,13,49.6,100-49.6,26,16);
		constSiteStatistics cnn=new constSiteStatistics("CNN",10.2+16.1+16.7,19.4+17.4,48+27,16,48.6,100-48.6,24,2.2);
		constSiteStatistics nytimes=new constSiteStatistics("NY Times",9.2+15.1+16.7,19.4+17.4,47+26,17,47.6,100-47.6,23,3.1);
		constSiteStatistics hfngtn=new constSiteStatistics("Hfngtn",10.2+16.1+16.7,19.4+17.4,47+27,17,46.6,100-46.6,22,8.1);
		constSiteStatistics msn=new constSiteStatistics("MSN",10.2+16.1+16.7,19.4+17.4,49+27,16,47.6,100-47.6,25,18.2);
		constSiteStatistics fox=new constSiteStatistics("FOX",9.2+15.1+16.7,19.4+18.4,46+26,18,48.6,100-48.6,24,3.1);

		constSiteStatistics amazon=new constSiteStatistics("Amazon",9.2+15.1+16.7,19.4+18.4,50+27,15,47.6,100-47.6,21,12.8);
		constSiteStatistics ebay=new constSiteStatistics("Ebay",9.2+16.1+15.7,19.4+17.4,50+27,15,48.6,100-48.6,22,8.5);
		constSiteStatistics walmart=new constSiteStatistics("Wal-Mart",7.2+15.1+16.7,20.4+18.4,47+28,19,45.6,100-45.6,18,3.8);
		constSiteStatistics target=new constSiteStatistics("Target",9.2+17.1+17.7,18.4+17.4,45+27,19,45.6,100-45.6,19,2.0);
		constSiteStatistics bestbuy=new constSiteStatistics("BestBuy",10.2+14.1+16.7,20.4+17.4,46.5+26,18,47.6,100-47.6,20,1.6);
		constSiteStatistics sears=new constSiteStatistics("Sears",9.2+12.1+16.7,20.4+18.4,45+25,20,46.6,100-46.6,19,1.6);
		
		constSiteStatistics webmd=new constSiteStatistics("WebMD",9.2+15.1+15.7,19.4+18.4,46+26.5,18.5,45.6,100-45.6,24,2.5);
		constSiteStatistics ehow=new constSiteStatistics("EHow",10.2+15.1+15.7,19.4+17.4,50+27,15,47.6,100-47.6,28,2.5);
		constSiteStatistics ask=new constSiteStatistics("Ask",10.2+13.1+15.7,20.4+18.4,50+28,15,48.6,100-48.6,28,5.0);
		constSiteStatistics tripadvisor=new constSiteStatistics("TripAdvisor",8.2+16.1+17.7,20.4+17.4,46.5+26,17.5,46.6,100-46.6,30,1.6);
		constSiteStatistics cnet=new constSiteStatistics("CNet",12.2+15.1+15.7,18.4+17.4,48+26.5,16.5,50.6,100-50.6,27,1.7);
		constSiteStatistics weather=new constSiteStatistics("Weather",9.2+15.1+16.7,20.4+18.4,45.5+26.5,18.5,47.6,100-47.6,31,5.8);

		newsSites={yahoo,cnn,nytimes,hfngtn,msn,fox};
		shoppingSites={amazon,ebay,walmart,target,bestbuy,sears};
		webInformationSites={webmd,ehow,ask,tripadvisor,cnet,weather};
	}
	
	public todayStatistics(AdxPublisherReport adxPublisherReport)
	{
		
	}


	
}
