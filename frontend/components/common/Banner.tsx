"use client";

const bannerImageList = [
  {
    title: "브라질 리오",
    imageUrl: "/image/브라질 리오.jpg",
    description: "브라질에서 멋진 석양을 즐겨볼까요?",
  },
  {
    title: "아이슬란드",
    imageUrl: "/image/아이슬란드 블랙 샌드 비치.jpg",
    description: "밤이 오지 않는 바다는 어때요?",
  },
  {
    title: "이탈리아",
    imageUrl: "/image/이탈리아.jpg",
    description: "지중해에서 추억을 새겨보는건요?",
  },
  {
    title: "일본 교토",
    imageUrl: "/image/일본 교토.jpg",
    description: "가깝지만 먼나라 일본 이 기회에?",
  },
  {
    title: "독일",
    imageUrl: "/image/독일 성.jpg",
    description: "음하하 내가 이 성의 성주다!",
  },
];

import Image from "next/image";
import { useState, useEffect, useRef, useCallback } from "react";

const Banner = () => {
  const bannerContainerRef = useRef<HTMLDivElement>(null);
  const [currentIndex, setCurrentIndex] = useState(0);
  const intervalRef = useRef<NodeJS.Timeout | null>(null);
  const [isScrolling, setIsScrolling] = useState(false);
  const [bannerWidth, setBannerWidth] = useState(0);

  const clearExistingInterval = useCallback(() => {
    if (intervalRef.current) {
      clearInterval(intervalRef.current);
      intervalRef.current = null;
    }
  }, []);

  const updateBannerWidth = useCallback(() => {
    if (bannerContainerRef.current) {
      setBannerWidth(bannerContainerRef.current.clientWidth);
    }
  }, []);

  const scroll = useCallback(
    (direction: "left" | "right") => {
      if (isScrolling) return;

      const banner = bannerContainerRef.current;
      if (banner) {
        setIsScrolling(true);
        const scrollAmount = direction === "left" ? -bannerWidth : bannerWidth;
        banner.scrollBy({ left: scrollAmount, behavior: "smooth" });

        setTimeout(() => {
          setIsScrolling(false);
        }, 1000);
      }
    },
    [isScrolling, bannerWidth]
  );

  const startAutoScroll = useCallback(() => {
    clearExistingInterval();
    intervalRef.current = setInterval(() => {
      setCurrentIndex(prevIndex => (prevIndex + 1) % bannerImageList.length);
    }, 7000);
  }, [clearExistingInterval]);

  useEffect(() => {
    if (typeof window !== "undefined") {
      const banner = bannerContainerRef.current;

      startAutoScroll();
      updateBannerWidth();

      if (banner) {
        banner.addEventListener("mouseenter", clearExistingInterval);
        banner.addEventListener("mouseleave", startAutoScroll);
      }

      window.addEventListener("resize", updateBannerWidth);

      return () => {
        clearExistingInterval();
        if (banner) {
          banner.removeEventListener("mouseenter", clearExistingInterval);
          banner.removeEventListener("mouseleave", startAutoScroll);
        }
        window.removeEventListener("resize", updateBannerWidth);
      };
    }
  }, [startAutoScroll, clearExistingInterval, updateBannerWidth]);

  useEffect(() => {
    const banner = bannerContainerRef.current;
    if (banner) {
      banner.scrollTo({
        left: currentIndex * bannerWidth,
        behavior: "smooth",
      });
    }
  }, [currentIndex, bannerWidth]);

  return (
    <div className="relative h-full min-h-[240px] w-full overflow-hidden">
      <div ref={bannerContainerRef} className="flex h-full min-h-[240px] w-full overflow-hidden">
        {bannerImageList.map((image, idx) => (
          <div key={idx} className="relative w-full flex-none">
            <Image className="absolute object-cover" fill src={image.imageUrl} alt="Banner_image" priority />
            <div className="absolute h-full w-full bg-black opacity-40" />
            <div className="absolute h-full w-full p-4 text-white top-[10%] left-[10%]">
              <div className="flex flex-col gap-6">
                <div
                  className="text-[50px] font-bold font-roboto"
                  style={{ textShadow: "0 0 3px black, 0 0 4px white" }}>
                  {image.title}
                </div>
                <div className="text-[20px] font-roboto ml-5">{image.description}</div>
              </div>
            </div>
          </div>
        ))}

        <div
          className="absolute left-4 top-1/2 -translate-y-1/2 cursor-pointer bg-white bg-opacity-50 p-4 border-2 rounded-lg text-white"
          onClick={() => scroll("left")}>
          {"<"}
        </div>
        <div
          className="absolute right-4 top-1/2 -translate-y-1/2 cursor-pointer bg-white bg-opacity-50 p-4 border-2 rounded-lg text-white"
          onClick={() => scroll("right")}>
          {">"}
        </div>
      </div>
    </div>
  );
};

export default Banner;
