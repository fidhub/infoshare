package infoshare.app.util;

/**
 * Created by Songezo on 2016-10-01.
 */
public enum AboutText {
    about {
        @Override
        public String toString() {

            return "What is InfoShare?<br>" +
                    "InfoShare is an online platform where Caregivers share Information on their experiences and expertise to other Caregivers within the same organization. InfoShare bridges the gap between community caregivers and the department of health along with all the healthcare facilities within in the city<br>" +
                    "<br>" +
                    "The System has 4 Roles:<br>" +
                    "<ul>"+
                    "<li>The Organization Admin</li>" +
                    "<li>The Publisher of articles</li>" +
                    "<li>The Editor of articles</li>" +
                    "<li>The Author who writes articles</li>" +
                    "</ul>"+
                    "As described above, An Author is a caregiver who deals with the community on a day to day basis and writes articles on their discoveries within their field of expertise The Editor receives the Authors article and makes the necessary changes the them The Publisher Receives the edited articles and decides whether to, publish them to the InfoShare site where other caregivers can read them, or not The Articles can be done in the form of multiple media formats, Text, Pdf, Audio or Images.<br>" +
                    "<br>" +
                    "When the articles are published to the InfoShare platform, Caregivers within that organization can view, Add to the article in the form of a comment and further discuss the article.<br>" +
                    "<br>" +
                    "The Roles of Admin within the organization has the ability to add other roles and authors within the organization. The Organization Admin, Publisher and Editor has to be selected by the organization. The organization Admin can reset users passwords but has to request a password reset from the Admin of InfoShare for themselves.<br>" +
                    "<br>" +
                    "Organizations that invest in InfoShare has their own part of InfoShare that will be privately for that organization, meaning another organization will not be able to view each others shared articles.<br>" +
                    "<br>" +
                    "InfoShare, for now, is primarily being designed for healthcare providers.<br>" +
                    "<br>" +
                    "The admin of Infoshare itself is an organization with Authors, Editors and Publishers<br>" +
                    "<br>" +
                    "InfoShare is working with the Grabouw community at the moment in terms of understanding their health needs<br>" +
                    "<br>" +
                    "More Infomation about InfoShare with be noted as the project continues to grow";
        }
    },

}
