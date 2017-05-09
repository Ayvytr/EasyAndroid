package com.ayvytr.easyandroidtest.network;

import com.ayvytr.easyandroid.tools.TextTool;

import java.util.List;

/**
 * Created by Do on 2017/5/9.
 */

public class News
{

    private List<ContentBean> T1348647853363;

    public List<ContentBean> getT1348647853363()
    {
        return T1348647853363;
    }

    public void setT1348647853363(List<ContentBean> T1348647853363)
    {
        this.T1348647853363 = T1348647853363;
    }

    public static class ContentBean
    {
        /**
         * postid : PHOT24PH8000100A
         * hasCover : false
         * hasHead : 1
         * replyCount : 38594
         * hasImg : 1
         * digest :
         * hasIcon : true
         * docid : 9IG74V5H00963VRO_CK15B1A4bjjikeupdateDoc
         * title : 汶川地震9周年 航拍地震遗迹现状
         * order : 1
         * priority : 354
         * lmodify : 2017-05-09 20:28:15
         * boardid : photoview_bbs
         * ads : [{"title":"\"锁车神器\"整治电车乱停 强行启动爆胎","skipID":"00AP0001|2254395","tag":"photoset","imgsrc":"http://cms-bucket.nosdn.127.net/1e9f3ee79aea46b0b69af122da94f79720170509205226.jpeg","subtitle":"","skipType":"photoset","url":"00AP0001|2254395"},{"title":"航拍青岛大沽河河床 已断流近4年","skipID":"00AP0001|2254396","tag":"photoset","imgsrc":"http://cms-bucket.nosdn.127.net/e64710a7fd884c0a9d00bc1a43478d5820170509205324.jpeg","subtitle":"","skipType":"photoset","url":"00AP0001|2254396"},{"title":"莫斯科红场阅兵 北极装备首次亮相","skipID":"00AO0001|2254377","tag":"photoset","imgsrc":"http://cms-bucket.nosdn.127.net/38c8874597a84d619c2a473ef0796ed620170509195716.jpeg","subtitle":"","skipType":"photoset","url":"00AO0001|2254377"},{"title":"盘点\"一带一路\"国际合作重大工程项目","skipID":"00AO0001|2254373","tag":"photoset","imgsrc":"http://cms-bucket.nosdn.127.net/96fe46397dfb429e9224aef37c81190420170509194006.jpeg","subtitle":"","skipType":"photoset","url":"00AO0001|2254373"},{"title":"多地举行不朽军团游行 纪念二战老兵","skipID":"00AO0001|2254357","tag":"photoset","imgsrc":"http://cms-bucket.nosdn.127.net/ee27f782dffc4444a292f9c1034bf9a720170509172236.jpeg","subtitle":"","skipType":"photoset","url":"00AO0001|2254357"}]
         * photosetID : 00AN0001|2254376
         * imgsum : 15
         * topic_background : http://img2.cache.netease.com/m/newsapp/reading/cover1/C1348646712614.jpg
         * template : normal1
         * votecount : 35678
         * skipID : 00AN0001|2254376
         * alias : Top News
         * skipType : photoset
         * cid : C1348646712614
         * hasAD : 1
         * imgextra : [{"imgsrc":"http://cms-bucket.nosdn.127.net/c3d145be4bae4fe6a1503e19caf212ef20170509195403.jpeg"},{"imgsrc":"http://cms-bucket.nosdn.127.net/0206094c61f2476b87edcd61b75e420b20170509195252.jpeg"}]
         * source : 网易综合
         * ename : iosnews
         * tname : 头条
         * imgsrc : http://cms-bucket.nosdn.127.net/8d506dba0985430fb9188b2cf784856520170509195323.jpeg
         * ptime : 2017-05-09 19:54:16
         * url_3w : http://news.163.com/17/0509/19/CK14B5M8000189FH.html
         * ltitle : 习近平同法国当选总统马克龙通电话
         * url : http://3g.163.com/news/17/0509/19/CK14B5M8000189FH.html
         * subtitle :
         * specialID : S1491221897055
         * videosource : 其他
         * TAGS : 视频
         * imgType : 1
         * videoID : VCJ2UH626
         * length : 41
         * videoinfo : {"replyCount":100,"videosource":"其他","mp4Hd_url":"http://flv2.bn.netease.com/videolib3/1705/09/gjhSu6986/HD/gjhSu6986-mobile.mp4","title":"俄罗斯北极军事装备首次接受检阅","playCount":0,"replyBoard":"video_news_bbs","sectiontitle":"","replyid":"CJ2UH626000136D3","description":"俄罗斯北极军事装备首次接受检阅","mp4_url":"http://flv2.bn.netease.com/videolib3/1705/09/gjhSu6986/SD/gjhSu6986-mobile.mp4","length":41,"playersize":0,"m3u8Hd_url":"http://flv2.bn.netease.com/videolib3/1705/09/gjhSu6986/HD/movie_index.m3u8","vid":"VCJ2UH626","ptime":"2017-05-09 16:04:44","m3u8_url":"http://flv2.bn.netease.com/videolib3/1705/09/gjhSu6986/SD/movie_index.m3u8"}
         */

        private String postid;
        private boolean hasCover;
        private int hasHead;
        private int replyCount;
        private int hasImg;
        private String digest;
        private boolean hasIcon;
        private String docid;
        private String title;
        private int order;
        private int priority;
        private String lmodify;
        private String boardid;
        private String photosetID;
        private int imgsum;
        private String topic_background;
        private String template;
        private int votecount;
        private String skipID;
        private String alias;
        private String skipType;
        private String cid;
        private int hasAD;
        private String source;
        private String ename;
        private String tname;
        private String imgsrc;
        private String ptime;
        private String url_3w;
        private String ltitle;
        private String url;
        private String subtitle;
        private String specialID;
        private String videosource;
        private String TAGS;
        private int imgType;
        private String videoID;
        private int length;
        private VideoinfoBean videoinfo;
        private List<AdsBean> ads;
        private List<ImgextraBean> imgextra;

        public String getPostid()
        {
            return postid;
        }

        public void setPostid(String postid)
        {
            this.postid = postid;
        }

        public boolean isHasCover()
        {
            return hasCover;
        }

        public void setHasCover(boolean hasCover)
        {
            this.hasCover = hasCover;
        }

        public int getHasHead()
        {
            return hasHead;
        }

        public void setHasHead(int hasHead)
        {
            this.hasHead = hasHead;
        }

        public int getReplyCount()
        {
            return replyCount;
        }

        public void setReplyCount(int replyCount)
        {
            this.replyCount = replyCount;
        }

        public int getHasImg()
        {
            return hasImg;
        }

        public void setHasImg(int hasImg)
        {
            this.hasImg = hasImg;
        }

        public String getDigest()
        {
            if(TextTool.isEmpty(digest))
            {
                return TextTool.emptyString();
            }

            return digest;
        }

        public void setDigest(String digest)
        {
            this.digest = digest;
        }

        public boolean isHasIcon()
        {
            return hasIcon;
        }

        public void setHasIcon(boolean hasIcon)
        {
            this.hasIcon = hasIcon;
        }

        public String getDocid()
        {
            return docid;
        }

        public void setDocid(String docid)
        {
            this.docid = docid;
        }

        public String getTitle()
        {
            return title;
        }

        public void setTitle(String title)
        {
            this.title = title;
        }

        public int getOrder()
        {
            return order;
        }

        public void setOrder(int order)
        {
            this.order = order;
        }

        public int getPriority()
        {
            return priority;
        }

        public void setPriority(int priority)
        {
            this.priority = priority;
        }

        public String getLmodify()
        {
            return lmodify;
        }

        public void setLmodify(String lmodify)
        {
            this.lmodify = lmodify;
        }

        public String getBoardid()
        {
            return boardid;
        }

        public void setBoardid(String boardid)
        {
            this.boardid = boardid;
        }

        public String getPhotosetID()
        {
            return photosetID;
        }

        public void setPhotosetID(String photosetID)
        {
            this.photosetID = photosetID;
        }

        public int getImgsum()
        {
            return imgsum;
        }

        public void setImgsum(int imgsum)
        {
            this.imgsum = imgsum;
        }

        public String getTopic_background()
        {
            return topic_background;
        }

        public void setTopic_background(String topic_background)
        {
            this.topic_background = topic_background;
        }

        public String getTemplate()
        {
            return template;
        }

        public void setTemplate(String template)
        {
            this.template = template;
        }

        public int getVotecount()
        {
            return votecount;
        }

        public void setVotecount(int votecount)
        {
            this.votecount = votecount;
        }

        public String getSkipID()
        {
            return skipID;
        }

        public void setSkipID(String skipID)
        {
            this.skipID = skipID;
        }

        public String getAlias()
        {
            return alias;
        }

        public void setAlias(String alias)
        {
            this.alias = alias;
        }

        public String getSkipType()
        {
            return skipType;
        }

        public void setSkipType(String skipType)
        {
            this.skipType = skipType;
        }

        public String getCid()
        {
            return cid;
        }

        public void setCid(String cid)
        {
            this.cid = cid;
        }

        public int getHasAD()
        {
            return hasAD;
        }

        public void setHasAD(int hasAD)
        {
            this.hasAD = hasAD;
        }

        public String getSource()
        {
            return source;
        }

        public void setSource(String source)
        {
            this.source = source;
        }

        public String getEname()
        {
            return ename;
        }

        public void setEname(String ename)
        {
            this.ename = ename;
        }

        public String getTname()
        {
            return tname;
        }

        public void setTname(String tname)
        {
            this.tname = tname;
        }

        public String getImgsrc()
        {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc)
        {
            this.imgsrc = imgsrc;
        }

        public String getPtime()
        {
            return ptime;
        }

        public void setPtime(String ptime)
        {
            this.ptime = ptime;
        }

        public String getUrl_3w()
        {
            return url_3w;
        }

        public void setUrl_3w(String url_3w)
        {
            this.url_3w = url_3w;
        }

        public String getLtitle()
        {
            return ltitle;
        }

        public void setLtitle(String ltitle)
        {
            this.ltitle = ltitle;
        }

        public String getUrl()
        {
            return url;
        }

        public void setUrl(String url)
        {
            this.url = url;
        }

        public String getSubtitle()
        {
            return subtitle;
        }

        public void setSubtitle(String subtitle)
        {
            this.subtitle = subtitle;
        }

        public String getSpecialID()
        {
            return specialID;
        }

        public void setSpecialID(String specialID)
        {
            this.specialID = specialID;
        }

        public String getVideosource()
        {
            return videosource;
        }

        public void setVideosource(String videosource)
        {
            this.videosource = videosource;
        }

        public String getTAGS()
        {
            return TAGS;
        }

        public void setTAGS(String TAGS)
        {
            this.TAGS = TAGS;
        }

        public int getImgType()
        {
            return imgType;
        }

        public void setImgType(int imgType)
        {
            this.imgType = imgType;
        }

        public String getVideoID()
        {
            return videoID;
        }

        public void setVideoID(String videoID)
        {
            this.videoID = videoID;
        }

        public int getLength()
        {
            return length;
        }

        public void setLength(int length)
        {
            this.length = length;
        }

        public VideoinfoBean getVideoinfo()
        {
            return videoinfo;
        }

        public void setVideoinfo(VideoinfoBean videoinfo)
        {
            this.videoinfo = videoinfo;
        }

        public List<AdsBean> getAds()
        {
            return ads;
        }

        public void setAds(List<AdsBean> ads)
        {
            this.ads = ads;
        }

        public List<ImgextraBean> getImgextra()
        {
            return imgextra;
        }

        public void setImgextra(List<ImgextraBean> imgextra)
        {
            this.imgextra = imgextra;
        }

        public static class VideoinfoBean
        {
            /**
             * replyCount : 100
             * videosource : 其他
             * mp4Hd_url : http://flv2.bn.netease.com/videolib3/1705/09/gjhSu6986/HD/gjhSu6986-mobile.mp4
             * title : 俄罗斯北极军事装备首次接受检阅
             * playCount : 0
             * replyBoard : video_news_bbs
             * sectiontitle :
             * replyid : CJ2UH626000136D3
             * description : 俄罗斯北极军事装备首次接受检阅
             * mp4_url : http://flv2.bn.netease.com/videolib3/1705/09/gjhSu6986/SD/gjhSu6986-mobile.mp4
             * length : 41
             * playersize : 0
             * m3u8Hd_url : http://flv2.bn.netease.com/videolib3/1705/09/gjhSu6986/HD/movie_index.m3u8
             * vid : VCJ2UH626
             * ptime : 2017-05-09 16:04:44
             * m3u8_url : http://flv2.bn.netease.com/videolib3/1705/09/gjhSu6986/SD/movie_index.m3u8
             */

            private int replyCount;
            private String videosource;
            private String mp4Hd_url;
            private String title;
            private int playCount;
            private String replyBoard;
            private String sectiontitle;
            private String replyid;
            private String description;
            private String mp4_url;
            private int length;
            private int playersize;
            private String m3u8Hd_url;
            private String vid;
            private String ptime;
            private String m3u8_url;

            public int getReplyCount()
            {
                return replyCount;
            }

            public void setReplyCount(int replyCount)
            {
                this.replyCount = replyCount;
            }

            public String getVideosource()
            {
                return videosource;
            }

            public void setVideosource(String videosource)
            {
                this.videosource = videosource;
            }

            public String getMp4Hd_url()
            {
                return mp4Hd_url;
            }

            public void setMp4Hd_url(String mp4Hd_url)
            {
                this.mp4Hd_url = mp4Hd_url;
            }

            public String getTitle()
            {
                return title;
            }

            public void setTitle(String title)
            {
                this.title = title;
            }

            public int getPlayCount()
            {
                return playCount;
            }

            public void setPlayCount(int playCount)
            {
                this.playCount = playCount;
            }

            public String getReplyBoard()
            {
                return replyBoard;
            }

            public void setReplyBoard(String replyBoard)
            {
                this.replyBoard = replyBoard;
            }

            public String getSectiontitle()
            {
                return sectiontitle;
            }

            public void setSectiontitle(String sectiontitle)
            {
                this.sectiontitle = sectiontitle;
            }

            public String getReplyid()
            {
                return replyid;
            }

            public void setReplyid(String replyid)
            {
                this.replyid = replyid;
            }

            public String getDescription()
            {
                return description;
            }

            public void setDescription(String description)
            {
                this.description = description;
            }

            public String getMp4_url()
            {
                return mp4_url;
            }

            public void setMp4_url(String mp4_url)
            {
                this.mp4_url = mp4_url;
            }

            public int getLength()
            {
                return length;
            }

            public void setLength(int length)
            {
                this.length = length;
            }

            public int getPlayersize()
            {
                return playersize;
            }

            public void setPlayersize(int playersize)
            {
                this.playersize = playersize;
            }

            public String getM3u8Hd_url()
            {
                return m3u8Hd_url;
            }

            public void setM3u8Hd_url(String m3u8Hd_url)
            {
                this.m3u8Hd_url = m3u8Hd_url;
            }

            public String getVid()
            {
                return vid;
            }

            public void setVid(String vid)
            {
                this.vid = vid;
            }

            public String getPtime()
            {
                return ptime;
            }

            public void setPtime(String ptime)
            {
                this.ptime = ptime;
            }

            public String getM3u8_url()
            {
                return m3u8_url;
            }

            public void setM3u8_url(String m3u8_url)
            {
                this.m3u8_url = m3u8_url;
            }
        }

        public static class AdsBean
        {
            /**
             * title : "锁车神器"整治电车乱停 强行启动爆胎
             * skipID : 00AP0001|2254395
             * tag : photoset
             * imgsrc : http://cms-bucket.nosdn.127.net/1e9f3ee79aea46b0b69af122da94f79720170509205226.jpeg
             * subtitle :
             * skipType : photoset
             * url : 00AP0001|2254395
             */

            private String title;
            private String skipID;
            private String tag;
            private String imgsrc;
            private String subtitle;
            private String skipType;
            private String url;

            public String getTitle()
            {
                return title;
            }

            public void setTitle(String title)
            {
                this.title = title;
            }

            public String getSkipID()
            {
                return skipID;
            }

            public void setSkipID(String skipID)
            {
                this.skipID = skipID;
            }

            public String getTag()
            {
                return tag;
            }

            public void setTag(String tag)
            {
                this.tag = tag;
            }

            public String getImgsrc()
            {
                return imgsrc;
            }

            public void setImgsrc(String imgsrc)
            {
                this.imgsrc = imgsrc;
            }

            public String getSubtitle()
            {
                return subtitle;
            }

            public void setSubtitle(String subtitle)
            {
                this.subtitle = subtitle;
            }

            public String getSkipType()
            {
                return skipType;
            }

            public void setSkipType(String skipType)
            {
                this.skipType = skipType;
            }

            public String getUrl()
            {
                return url;
            }

            public void setUrl(String url)
            {
                this.url = url;
            }
        }

        public static class ImgextraBean
        {
            /**
             * imgsrc : http://cms-bucket.nosdn.127.net/c3d145be4bae4fe6a1503e19caf212ef20170509195403.jpeg
             */

            private String imgsrc;

            public String getImgsrc()
            {
                return imgsrc;
            }

            public void setImgsrc(String imgsrc)
            {
                this.imgsrc = imgsrc;
            }
        }
    }
}
