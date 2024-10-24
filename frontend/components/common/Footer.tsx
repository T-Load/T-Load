import Image from "next/image";
import Link from "next/link";

const Footer = () => {
  return (
    <div className="grid bg-black opacity-90 w-full h-[100px] mt-6 text-white [grid-template-areas:'copyright_sns_githubInfo''footer_footer_forWhat']">
      <div className="p-2 font-roboto text-lg" style={{ gridArea: "copyright" }}>
        Copyright T-Load @2024
      </div>

      <div className="p-4 flex justify-center gap-6 " style={{ gridArea: "sns" }}>
        <Link href={"https://www.facebook.com"}>
          <Image width={22} height={22} src={"/icons/facebook.png"} alt="페이스북" />
        </Link>
        <Link href={"https://www.instagram.com/"}>
          <Image width={22} height={22} src={"/icons/instagram.png"} alt="인스타그램" />
        </Link>
        <Link href={"https://www.twitter.com/"}>
          <Image width={22} height={22} src={"/icons/twitter.png"} alt="트위터" />
        </Link>
      </div>

      <div className="p-2 gap-4 flex justify-end mr-4" style={{ gridArea: "githubInfo" }}>
        <p className="font-roboto mt-1">How we work?</p>
        <Link href={"https://github.com/T-Load/T-Load"}>
          <Image width={22} height={22} src={"/icons/github.png"} alt="깃헙" />
        </Link>
      </div>
      <div className="p-4 flex justify-end items-end" style={{ gridArea: "forWhat" }}>
        For What Section
      </div>
    </div>
  );
};

export default Footer;
