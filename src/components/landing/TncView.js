import { Button, DialogActions, DialogContent, DialogContentText } from '@mui/material';
import React from 'react';

const   TnC = (props) => {
    const descriptionElementRef = React.useRef(null);
    return(
    <React.Fragment style={{outerHeight:800}}>
        <DialogContent dividers={true}>
        <DialogContentText id="scroll-dialog-description" ref={descriptionElementRef} tabIndex={-1}>
    <div>
        <p><strong>Subscription Renewal</strong></p>

        <p>Subscription will be renewed automatically 7 days prior to the end of each subscription period except the free/trial.</p>
        <br></br>
        <p><strong>Rules &amp; Regulations</strong></p>

        <p>The following are terms of a legal agreement (&ldquo;Agreement&rdquo;) between you and Solute., its Parent Company, its subsidiaries and affiliates (collectively referred as &ldquo;Solute&rdquo;). By accessing, browsing and/or using this website (&ldquo;Site&rdquo;) or by accessing or using Solute&rsquo;s systems, products, or services (cumulatively, the &ldquo;Services&rdquo;), you acknowledge that you have read, understood and agree to be bound by these terms and any other terms that Solute may make available to you from time to time and to comply with all applicable laws and regulations. If you do not agree with these terms, do not use this Site or the Services. This Site may contain other proprietary notices and copyright information, the terms of which must be observed and followed. Information on this Site may contain technical inaccuracies or typographical errors. Please read this Agreement carefully and be aware that Solute may, in its sole discretion and without notice, revise these terms at any time by updating this posting.</p>
        <br></br>
        <p><strong>Copyrights and Use of Site Content</strong></p>

        <p>All content included or available on this Site and the Services, including the site design, text, graphics, interfaces, and the selection and arrangements thereof, is the property of Solute (or its licensors) and is protected by the Intellectual Property Rights Regime / Legal Framework. Except as stated herein, none of the materials may be copied, reproduced, distributed, republished, downloaded, displayed, posted or transmitted in any form or by any means, including, but not limited to, electronic, mechanical, photocopying, recording or otherwise, without the prior written permission of Solute or the copyright owner. For the applicable term of your license, and subject to the other terms and conditions set forth herein, permission is granted to download one copy of the materials on this Site on a single computer for your internal business purposes only, provided that you do not modify the materials and that we retain all copyright and other proprietary notices contained in the materials. The foregoing permission is non-transferable and non-exclusive. This permission terminates immediately if you breach this Agreement or at the expiry of your subscription / Agreement (whichever is earlier). You may not &ldquo;mirror&rdquo; any material contained on this Site without Solute&rsquo;s express written permission. Any unauthorized use of the materials contained on this Site may violate copyright laws, trademark laws, the laws of privacy and publicity and/or communications regulations and statutes and will attract applicable penalties &ndash; as prescribed under law.&nbsp;</p>

        <p>Except for the limited license granted under the above paragraph, no right, title, or interest of intellectual property or other proprietary rights in and to the Services or Site made available under this Agreement is being transferred to you hereunder. Solute and its licensors retain all right, title and interest, including, without limitation, all copyright and other proprietary rights in and to the Services and Site, and to all, modifications, enhancements, and derivatives thereof. All rights not expressly granted are hereby reserved.</p>

        <p>You shall not: (a) sell, lease, license or sublicense the Services; (b) modify, change, alter, translate, create derivative works from, reverse engineer, disassemble or decompile the Services or any software included in the Services; (c) provide, disclose, divulge or make available to, or permit use of the Services by, any third party; (d) copy or reproduce all or any part of the Services; (e) interfere, or attempt to interfere, with the Services in any way; (f) use the Services to engage in spamming, mail bombing, spoofing or any other fraudulent, illegal or unauthorized use of the Services; (g) introduce into or transmit through the Services any virus, worm, trap door, back door; or (h) remove, obscure or alter any copyright notice, trademarks or other proprietary rights notices affixed to or contained within the Services.</p>

        <p>Only your employees and authorized third party consultants (the &ldquo;Authorized Users&rdquo;) may use this Site and/or the Services. From time to time, Solute may, in its sole and absolute discretion, issue logon identifier and password to control and monitor your use of the Site and the Services. In some instances, Solute may issue to one Authorized User (&ldquo;Administrator&rdquo;) an individual logon identifier and password (&ldquo;Administrator&rsquo;s Logon&rdquo;) for purposes of administering the Services. Using the Administrator&rsquo;s Logon, the Administrator shall assign each remaining Authorized User a unique logon identifier and password and assign and manage the business rules that control each such Authorized User&rsquo;s access to the Site and Services. You shall ensure that each Authorized User will: (a) not disclose their logon identifier to any person or entity; (b) not permit any other person or entity to use their logon identifier; and (c) use the Site and Services strictly in accordance with the terms and conditions set forth herein.</p>

        <p>For a period of 20 days from the termination of this Agreement or its expiry (by efflux of time), you may request Solute for a copy of the data/files that have been uploaded or otherwise saved to our database provided as part of the Services subscription purchased by you under this Agreement. An administrative fee of USD 500 (per database) will be charged for providing you this information. &nbsp;The administrative fee can be revised in future at our discretion. The format of delivery of such data/files shall be determined by Solute and the same be amended from time to time.</p>

        <p>You hereby represent, warrant, and covenant to Solute that: (a) you have the authority to execute this Agreement and perform your obligations hereunder; and (b) you and your Authorized Users will only use the Services and Site for lawful purposes and will not use the Services or Site to violate any law of any country or the intellectual property rights of any third party.</p>

        <p>You agree to hold, in strict confidence, any materials or information that you receive from Solute or become privy to as a result of your use of the Site or the Services. You agree that you shall disclose Solute&rsquo;s confidential information only to those of your employees or agents who are required to have access to it in furtherance of this Agreement and who agree in writing to protect such information against unauthorized use or disclosure.</p>

        <p>Solute may audit your use of the Site or Services. You agree to cooperate with Solute&rsquo;s audit and provide reasonable assistance and access to all information in your possession. You agree to pay, within 30 days of written notification, any fees applicable to your use of the Site or Services in excess of your license rights or permitted usage.&nbsp;</p>

        <p>You shall be responsible for the compliance of the terms and conditions set forth herein, and for the use of the Site and/or Services, by your employees, third party consultants, and agents. You agree to fully indemnify, defend, and hold harmless Solute and its affiliates, officers, directors, representatives, and agents from and against any breach of the terms set forth herein.</p>

        <p>Solute may terminate this Agreement without any liability whatsoever - if you or any of your employees or agents breach any term of this Agreement.</p>

        <p><strong>Trademarks</strong></p>

        <p>Solute.app and Solute are proprietary marks of Solute.</p>

        <p><strong>Notification of Claimed Copyright Infringement</strong></p>

        <p>Pursuant to Section 512(c) of the Copyright Revision Act, as enacted through the Digital Millennium Copyright Act, Solute and Solute.app designates the following individual as its agent for receipt of notifications of claimed copyright infringement.</p>

        <p>By Mail</p>

        <p>Sara Hauenstein</p>

        <p>1375 5th Street</p>

        <p>#232</p>

        <p>Sarasota FL 34236</p>

        <p>USA</p>

        <p>Main +1.212.547.9481</p>

        <p>Fax +1.415.462.5401</p>

        <p>Email: supprt@solute.com</p>

        <p><strong>No Services, Endorsement or Professional Consultation</strong></p>

        <p>There may be delays, omissions, or inaccuracies in information obtained by you through your use of this Site or the Services. The information that you obtain from us is being provided with the express understanding that it does not constitute the rendering of investment, consulting, legal, accounting, tax, career or other advice or services. Information on this Site or the Services should not be relied upon for making business, investment or other decisions or used as a substitute for consultation with professional advisors. Moreover, Solute does not represent or endorse the accuracy or reliability of any advice, opinion, statement, or other information displayed, uploaded, downloaded or distributed through this Site or Services provided by Solute, any user, information provider or any other person or entity. You acknowledge that any reliance upon such opinion, advice, statement, memorandum, or information shall be at your sole option and risk. Moreover, Solute does not grant you any license or other authorization to use this Site or the Services in any manner - if such use in whole or in part suggests or indicates (in any manner) that Solute promotes or endorses any third party&rsquo;s causes, ideas, political campaigns, political views, web sites, products or services.</p>

        <p><strong>Modifications and Interruption to Service</strong></p>

        <p>Solute may alter, suspend or discontinue this Site or the Services or your access to use this Site or the Services at any time for any reason without notice or liability to you or any third party. This Site or Services may become unavailable due to maintenance or malfunction of computer equipment or for other reasons and may result in damages to the user&rsquo;s systems or operations. Solute does not guarantee that the information contained on the site is or will be free from any virus. You shall install appropriate anti-virus softwares in your systems to protect your systems from any virus or other computer software code or subroutine designed to disable, erase, impair or otherwise damage the user&rsquo;s systems, software or data. Solute will not be liable in any manner whatsoever - for any loss or damage that you may suffer on this account.&nbsp;</p>

        <p><strong>Third-Party Sites</strong></p>

        <p>Solute makes no representations whatsoever about any other website which you may access through this site. When you access a non-Solute website, please understand that it is completely independent from Solute, and that Solute has no control over the content on that website, even if Solute provides information or services to the owner of that website or obtains products or services from the owner of that website. In addition, a link to a non-Solute website, product, or service does not mean that Solute endorses or accepts any responsibility for the content or the use thereof. Solute disclaims any and all liability and responsibility for such content on any such website. We advise you to take due care and caution to ensure that whatever you select for your use is free of such items as viruses, worms, trojan horses and other items of a destructive nature.</p>

        <p><strong>User Postings</strong></p>

        <p>You acknowledge and agree that Solute shall own and have the unrestricted right to use, publish, whether in electronic form or otherwise, distribute and exploit any and all information that you post or otherwise publish on this Site (&ldquo;Submissions&rdquo;). You hereby waive any and all claims against Solute for any alleged or actual infringements of any rights of privacy or publicity, moral rights, rights of attribution or any other intellectual property rights in connection with Solute&rsquo;s use and publication of such Submissions. This means that any information submitted by you at this Site will be owned by Solute and may be used by Solute for any purpose, in present or in the future, without making any payment to &nbsp;or obtaining any further authorization from you. In the event Solute&rsquo;s ownership of such Submissions is successfully contested, you automatically grant Solute a perpetual, royalty-free, non-exclusive, unrestricted, worldwide and irrevocable right and license to use, reproduce, modify, publish, translate, prepare derivative works based upon, distribute, perform or display such Submissions, in whole or in part, in any form, media or technology known or hereafter developed for any purpose, including, but not limited to, advertising and promotional purposes. Solute does not represent or endorse the accuracy or reliability of any Submissions displayed, uploaded, posted on any message board, or otherwise distributed through this Site by any user of this Site, information provider or any third party. Solute expressly disclaims any and all liability related to Submissions. You acknowledge that any reliance upon any such Submissions shall be at your sole risk. You covenant that you shall not post or otherwise publish on the Site any materials that: (i) are threatening, libelous, defamatory, or obscene; (ii) would constitute or encourage conduct that would constitute a criminal offense, give rise to civil liability, or otherwise violate law in any manner; (iii) infringe the intellectual property, privacy, or other rights of any third parties; (iv) contain a computer virus or other destructive element; (v) contain advertising; or (vi) constitute or contain false or misleading statements. Solute in its sole discretion reserves the right to refuse to post and the right to remove any information or Submission from this Site, in whole or in part, for any reason.</p>

        <p><strong>Privacy Policy</strong></p>

        <p>
            The privacy policy of Solute forms an integral part of these terms by necessary implication. We strongly advise you to peruse the same carefully. You may read the Privacy Policy by clicking at the following link:
            <a style={{ textDecoration: 'underline', cursor: 'pointer' }} onClick={props.showPrivacyPolicy}>Privacy Policy</a>
        </p>

        <p><strong>Disclaimer of Warranties</strong></p>

        <p>The site and all materials thereon are distributed on an &ldquo;as is &ndash; where is &rdquo; basis without warranties or representations of any kind. To the fullest extent permissible under applicable law, Solute disclaims all representations and warranties, express or implied, including, but not limited to, implied warranties of merchantability, fitness for particular purpose, title and non-infringement. Specifically, but without limitation, Solute does not represent or warrant that: (1) the information on this site is correct, accurate or reliable; (2) the functions contained on this site will be uninterrupted or error-free; or (3) defects will be corrected, or that this site or the server that makes them available are free of viruses or other harmful components. You hereby acknowledge that use of the site is at your sole risk. If you are a California resident, you hereby waive California Civil Code Section 1542 which provides: &ldquo;A general release does not extend to claims which the creditor does not know or suspect to exist in his favor at the time of executing the release, which if known by him must have materially affected his settlement with the debtor.&rdquo;</p>

        <p><strong>Compliance with Laws</strong></p>

        <p>You and the Users shall use the products, Site, and Services in strict compliance with all applicable laws and regulations. Without in any way limiting the generality of the foregoing provision, you acknowledge that you are familiar with and understand the provisions of the U.S. Foreign Corrupt Practices Act (the &ldquo;FCPA&rdquo;) and the U.K. Bribery Act of 2010 (&ldquo;UKBA&rdquo;) and agree to comply with their terms as well as any provisions of local law or Solute&rsquo;s corporate policies and procedures related thereto. You further understand the provisions relating to the FCPA and UKBA&rsquo;s prohibitions regarding the payment or giving of anything of value, including, but not limited to, payments, gifts, travel, entertainment and meals, either directly or indirectly, to an official of a foreign government or political party for the purpose of influencing an act or decision in his or her official capacity or inducing the official to use his or her party&rsquo;s influence with that government, to obtain or retain business involving the products, Site or Services. You agree to not violate or let anyone violate the FCPA or UKBA, and you agree that no payment you make will constitute a bribe, influence payment, kickback, rebate, or other payment that violates the FCPA, the UKBA, or any other applicable anticorruption or anti-bribery law.</p>

        <p>You further acknowledge and agree that the products and Services sold under this Agreement may be subject to restrictions and controls imposed by the United States Export Administration Act and the regulations thereunder. You hereby agree to comply with all applicable export and reexport control laws and regulations, including the Export Administration Regulations (&ldquo;EAR&rdquo;) maintained by the U.S. Department of Commerce, trade and economic sanctions maintained by the Treasury Department&rsquo;s Office of Foreign Assets Control, and the International Traffic in Arms Regulations (&ldquo;ITAR&rdquo;) maintained by the Department of State. Specifically, you covenant that you shall not, directly or indirectly, sell, export, reexport, transfer, divert, or otherwise dispose of any Services, products, software, or technology (including products derived from or based on such technology) received from Solute under this Agreement to any destination, entity, or person prohibited by the laws or regulations of the United States, without obtaining prior authorization from Solute and competent government authorities as required by those laws and regulations. These prohibitions include, but are not limited to the following: (i) the products and Services cannot be exported or reexported to any countries embargoes by the United States (currently including Cuba, Iran, North Korea, Sudan or Syria) which includes nationals of these countries employed by you; (ii) the products and Services cannot be exported or re-exported for military use in country group &lsquo;b&rsquo; prior to valid &lsquo;export license&rsquo; or valid &lsquo;license exception&rsquo;; (iii) engineers cannot have access to Solute&rsquo;s proprietary encryption source code; and (iv) the products or Services cannot be used for any prohibited end uses including any &lsquo;nuclear, biological or chemical weapon related activities&rsquo;. You agree to immediately notify Solute of any suspicious activities by any of your employee or contractor related to the products, Site or Services. You hereby agree to indemnify and hold harmless, to the fullest extent permitted by law, Solute and its licensors from and against any fines or penalties that may arise as a result of a breach of this provision. This clause shall survive termination or expiration of this Agreement.</p>

        <p><strong>Limitation of Liability</strong></p>

        <p>Under no circumstances shall Solute or any of its predecessors, successors, parents, subsidiaries, affiliates, licensors, officers, directors, shareholders, investors, employees, agents, representatives, attorneys and their respective heirs, successors and assigns be liable for any damages, including direct, incidental, punitive, special, consequential or exemplary damages (including any loss of profits, revenue, data, or data use) that directly or indirectly result from the use of or the inability to use this Site or the Services or the information obtained on this Site or obtained from your use of this Site or the Services, including for viruses alleged to have been obtained from the Site or Services, even if Solute has been advised of the possibility of such damages. In no event shall Solute&rsquo;s or any of its predecessors&rsquo;, successors&rsquo;, parents&rsquo;, licensors&rsquo;, subsidiaries&rsquo;, affiliates&rsquo;, officers&rsquo;, directors&rsquo;, shareholders&rsquo;, investors&rsquo;, employees&rsquo;, agents&rsquo;, representatives&rsquo;, attorneys&rsquo; and/or their respective heirs&rsquo;, successors&rsquo; and assigns&rsquo; total liability to you for any and all damages, losses, claims, and causes of action whether in contract, tort (including, but not limited to, negligence) or otherwise exceed $1. Some jurisdictions may not allow the exclusion of implied warranties in which case some of the above exclusions may not apply to all users.</p>

        <p><strong>Solute DOES NOT GUARANTEE THAT THE SERVICES, PRODUCTS OR SITE WILL PERFORM ERROR-FREE OR UNINTERRUPTED OR THAT Solute WILL CORRECT ALL ERRORS. TO THE EXTENT PERMITTED BY LAW, THESE WARRANTIES ARE EXCLUSIVE AND THERE ARE NO OTHER EXPRESS OR IMPLIED REPRESENTATIONS, WARRANTIES, OR CONDITIONS, INCLUDING WARRANTIES OR CONDITIONS OF MERCHANTABILITY, NON-INFRINGEMENT, AND/OR FITNESS FOR A PARTICULAR PURPOSE &ndash; WHETHER DISCLOSED TO US OR NOT.</strong></p>

        <p><strong>Indemnification</strong></p>

        <p>You hereby agree to indemnify, defend, and hold harmless Solute and all of its predecessors, successors, parents, subsidiaries, affiliates, licensors, officers, directors, shareholders, investors, employees, agents, representatives and attorneys and their respective heirs, successors and assigns (collectively, the &ldquo;Indemnified Parties&rdquo;) from and against any and all liability and costs, including, without limitation, reasonable attorneys&rsquo; fees, incurred by the Indemnified Parties in connection with any claim arising out of or relating to any breach by you or by your agents, contractors, or Users of this Agreement or the representations, disclosures, warranties, and covenants you have made by agreeing to the terms of this Agreement. You shall fully cooperate with us in defending any such claim. &nbsp;Solute reserves the right, at its own expense, to assume the exclusive defense and control of any matter subject to indemnification by you (as stated hereinabove).</p>

        <p><strong>Termination</strong></p>

        <p>This agreement can be terminated by any Party by providing 60 days&rsquo; prior written notice to the other Party.&nbsp;</p>

        <p><strong>Applicable Law &amp; Jurisdiction</strong></p>

        <p>This Agreement is governed and interpreted pursuant to the laws of the State of New York, United States of America, notwithstanding any principles of conflicts of law. You expressly agree that exclusive jurisdiction resides in the courts of the State of New York to the exclusion of any other Court anywhere. You further agree and expressly consent to the exercise of personal jurisdiction in the State of New York in connection with any dispute or claim involving Solute. If any part of these terms is unlawful, void, or unenforceable, that part will be deemed severable and will not affect the validity and enforceability of the remaining provisions.</p>

        <p>You may not assign this Agreement or transfer the Services or products or any interest therein without obtaining Solute&rsquo;s prior written consent.</p>

        <p><strong>Infringement Notices and Takedown</strong></p>

        <p>Solute prohibits the posting of any information that infringes or violates the copyright and/or other intellectual property rights (including rights of privacy and publicity) of any person or entity. If you believe that any material contained on this Site infringes your copyright, you should notify Solute of your copyright infringement claim in accordance with the procedure stated hereinafter. Solute will process notices of alleged infringement which it receives and will take appropriate action as required by the Digital Millennium Copyright Act (DMCA). Pursuant to Section 512(c) of the Copyright Revision Act, as enacted through the Digital Millennium Copyright Act, Solute.app designates the following individual as its agent for receipt of notifications of claimed copyright infringement.</p>

        <p>By Mail</p>

        <p>Sara Hauenstein</p>

        <p>1375 5th Street</p>

        <p>#232</p>

        <p>Sarasota FL 34236</p>

        <p>USA</p>

        <p>Main +1.212.547.9481</p>

        <p>Fax +1.415.462.5401</p>

        <p>Email: support@solute.com</p>

        <p>To be effective, the notification must be in writing and contain the following information (DMCA, 17 U.S.C. 512(c)(3)):</p>

        <ol>
            <li>Physical or electronic signature of a person authorized to act on behalf of the owner of an exclusive right that is allegedly infringed;</li>
            <li>Identification of the copyrighted work claimed to have been infringed, or, if multiple copyrighted works at a single online site are covered by a single notification, a representative list of such works at that site;</li>
            <li>Identification of the material that is claimed to be infringing or to be the subject of infringing activity and that is to be removed or access to which is to be disabled, and information reasonably sufficient to permit the service provider to locate the material;</li>
            <li>Information reasonably sufficient to permit the service provider to contact the complaining party, such as an address, telephone number, and, if available, an electronic mail address at which the complaining party may be contacted;</li>
            <li>A statement that the complaining party has a good faith belief that use of the material in the manner complained of is not authorized by the copyright owner, its agent, or the law;</li>
            <li>A statement that the information in the notification is accurate, and under penalty of perjury, that the complaining party is authorized to act on behalf of the owner of an exclusive right that is allegedly infringed.</li>
        </ol>

        <p><strong>Entire Agreement</strong></p>

        <p>This Agreement constitutes the entire agreement between you and Solute with respect to the subject matter of this Agreement and supersedes and replaces all prior or contemporaneous understandings or agreements, written or oral, regarding that subject matter. Any waiver of any provision of this Agreement will be effective only if in writing and signed by Solute.</p>
    </div>
    </DialogContentText>
    </DialogContent>
    <DialogActions sx={{justifyContent: 'end'}}>
        <Button variant="contained" color="info" onClick={props.handleClose}>Close</Button>
    </DialogActions>
    </React.Fragment>
);
    }

export default TnC;
