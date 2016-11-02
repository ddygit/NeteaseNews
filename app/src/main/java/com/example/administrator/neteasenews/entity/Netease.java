package com.example.administrator.neteasenews.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/10/29.
 */

public class Netease {


    public List<Ads> ads;
    public List<Imgextras> imgextra;
    public String alias, boardid, cid, digest, docid, ename, imgsrc, lmodify, photosetID,
            postid, ptime, skipID, skipType, source, template, title, tname;
    public int hasAD, hasHead, hasImg, imgsum, order, priority, replyCount, votecount;
    public boolean hasCover, hasIcon;

    public Netease(List<Ads> ads, List<Imgextras> imgextra, String alias, String boardid, String cid, String digest, String docid, String ename, String imgsrc, String lmodify, String photosetID, String postid, String ptime, String skipID, String skipType, String source, String template, String title, String tname, int hasAD, int hasHead, int hasImg, int imgsum, int order, int priority, int replyCount, int votecount, boolean hasCover, boolean hasIcon) {
        this.ads = ads;
        this.imgextra = imgextra;
        this.alias = alias;
        this.boardid = boardid;
        this.cid = cid;
        this.digest = digest;
        this.docid = docid;
        this.ename = ename;
        this.imgsrc = imgsrc;
        this.lmodify = lmodify;
        this.photosetID = photosetID;
        this.postid = postid;
        this.ptime = ptime;
        this.skipID = skipID;
        this.skipType = skipType;
        this.source = source;
        this.template = template;
        this.title = title;
        this.tname = tname;
        this.hasAD = hasAD;
        this.hasHead = hasHead;
        this.hasImg = hasImg;
        this.imgsum = imgsum;
        this.order = order;
        this.priority = priority;
        this.replyCount = replyCount;
        this.votecount = votecount;
        this.hasCover = hasCover;
        this.hasIcon = hasIcon;
    }

    @Override
    public String toString() {
        return "Netease{" +
                "ads=" + ads +
                ", imgextra=" + imgextra +
                ", alias='" + alias + '\'' +
                ", boardid='" + boardid + '\'' +
                ", cid='" + cid + '\'' +
                ", digest='" + digest + '\'' +
                ", docid='" + docid + '\'' +
                ", ename='" + ename + '\'' +
                ", imgsrc='" + imgsrc + '\'' +
                ", lmodify='" + lmodify + '\'' +
                ", photosetID='" + photosetID + '\'' +
                ", postid='" + postid + '\'' +
                ", ptime='" + ptime + '\'' +
                ", skipID='" + skipID + '\'' +
                ", skipType='" + skipType + '\'' +
                ", source='" + source + '\'' +
                ", template='" + template + '\'' +
                ", title='" + title + '\'' +
                ", tname='" + tname + '\'' +
                ", hasAD=" + hasAD +
                ", hasHead=" + hasHead +
                ", hasImg=" + hasImg +
                ", imgsum=" + imgsum +
                ", order=" + order +
                ", priority=" + priority +
                ", replyCount=" + replyCount +
                ", votecount=" + votecount +
                ", hasCover=" + hasCover +
                ", hasIcon=" + hasIcon +
                '}';
    }

    public class Ads {
        private String imgsrc,subtitle,tag,title,url;

        public Ads(String imgsrc, String subtitle, String tag, String title, String url) {
            this.imgsrc = imgsrc;
            this.subtitle = subtitle;
            this.tag = tag;
            this.title = title;
            this.url = url;
        }

        @Override
        public String toString() {
            return "Ads{" +
                    "imgsrc='" + imgsrc + '\'' +
                    ", subtitle='" + subtitle + '\'' +
                    ", tag='" + tag + '\'' +
                    ", title='" + title + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public class Imgextras {
        public Imgextras(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public String imgsrc;

    }

    public List<Ads> getAds() {
        return ads;
    }

    public void setAds(List<Ads> ads) {
        this.ads = ads;
    }

    public List<Imgextras> getImgextra() {
        return imgextra;
    }

    public void setImgextra(List<Imgextras> imgextra) {
        this.imgextra = imgextra;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getBoardid() {
        return boardid;
    }

    public void setBoardid(String boardid) {
        this.boardid = boardid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getLmodify() {
        return lmodify;
    }

    public void setLmodify(String lmodify) {
        this.lmodify = lmodify;
    }

    public String getPhotosetID() {
        return photosetID;
    }

    public void setPhotosetID(String photosetID) {
        this.photosetID = photosetID;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public String getSkipID() {
        return skipID;
    }

    public void setSkipID(String skipID) {
        this.skipID = skipID;
    }

    public String getSkipType() {
        return skipType;
    }

    public void setSkipType(String skipType) {
        this.skipType = skipType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public int getHasAD() {
        return hasAD;
    }

    public void setHasAD(int hasAD) {
        this.hasAD = hasAD;
    }

    public int getHasHead() {
        return hasHead;
    }

    public void setHasHead(int hasHead) {
        this.hasHead = hasHead;
    }

    public int getHasImg() {
        return hasImg;
    }

    public void setHasImg(int hasImg) {
        this.hasImg = hasImg;
    }

    public int getImgsum() {
        return imgsum;
    }

    public void setImgsum(int imgsum) {
        this.imgsum = imgsum;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public int getVotecount() {
        return votecount;
    }

    public void setVotecount(int votecount) {
        this.votecount = votecount;
    }

    public boolean isHasCover() {
        return hasCover;
    }

    public void setHasCover(boolean hasCover) {
        this.hasCover = hasCover;
    }

    public boolean isHasIcon() {
        return hasIcon;
    }

    public void setHasIcon(boolean hasIcon) {
        this.hasIcon = hasIcon;
    }
}
